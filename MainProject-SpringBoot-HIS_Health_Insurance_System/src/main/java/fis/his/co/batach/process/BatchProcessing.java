package fis.his.co.batach.process;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fis.his.co.batach.bao.BaoInterface;
import fis.his.co.batach.model.BatchRunModel;
import fis.his.co.batach.model.BatchSumryModel;
import fis.his.co.batach.service.BatchServiceInterface;
import fis.his.co.ed.model.EligibilityModel;
import fis.his.co.ed.model.TriggerModel;
import fis.his.co.ed.service.EligibilityInserface;

@Component("batchprocess")
public class BatchProcessing {

	private static final String BATCH_NAME = "Batch_DL";
	
	@Autowired
	private BatchServiceInterface batchService;

	@Autowired
	private BaoInterface baoservice;
	
	@Autowired
	private EligibilityInserface edService;
	
	private  Integer successTroggerCount=0;
	private  Integer failureTriggerCount=0;

	public void test() throws NumberFormatException, ClassNotFoundException {
		Long preProcess = this.preProcess();
		this.start();
		this.postProcess(preProcess);
	}
	
	public Long preProcess() {
		BatchRunModel brm = new BatchRunModel();
		brm.setBatchName(BATCH_NAME);
		brm.setBatchRunStatus("ST");
		brm.setStartDate(new Date());

		BatchRunModel result = batchService.insertIntoBatchRun(brm);
		return result.getBatchRunId();
	}

	public void start() throws NumberFormatException, ClassNotFoundException {
		// reading the pending data from Trigger class
		//List<TriggerModel> triggerList = edService.fetchDataWhereStatusIsPendig();
		List<TriggerModel> triggerList = baoservice.getTriggerByBucket("${batch.trigger_table.status}", Integer.parseInt("${batch.trigger_table.bucketsize}"), Integer.parseInt("${batch.trigger_table.bucketnumbe}"));
		// create multiple thread to run the batch
		ExecutorService service = Executors.newFixedThreadPool(Integer.parseInt("${thread.pool.count}"));
		//create thread pool
		ExecutorCompletionService<Object> pool = new ExecutorCompletionService<>(service);
		
		for(TriggerModel t : triggerList) {
			//submit task to thread
			pool.submit(new Callable<Object>() {
				@Override
				public Object call() throws Exception {
					process(t);
					return null;
				}
			});
		}
	}

	public void process(TriggerModel triggerModel) {
		try {
			EligibilityModel edModel = edService.fetchByCaseNumber(triggerModel.getCasenumber());
			if ("AP".equalsIgnoreCase(edModel.getPlanStatus())) {
				//PDF for approved plan
				Pdf.pdfGenForPlanAp(edModel);
			} else if ("DN".equalsIgnoreCase(edModel.getPlanStatus())) {
				// PDF for denied plan
				Pdf.pdfGenForPlanDn(edModel);
			  }
			edService.updateTrigger(triggerModel);
			successTroggerCount++;
		} catch (Exception e) {
			
			failureTriggerCount++;
		}
	}

	public void postProcess(Long runId) {
		BatchRunModel model = batchService.getBatchRun(runId);
		model.setBatchRunStatus("EN");
		model.setEndDate(new Date());
		batchService.updateBatchRunDetails(model);
		
		//Insert batch execution summary in batch summary table
		BatchSumryModel bsmodel = new BatchSumryModel();
		bsmodel.setBatchName(BATCH_NAME);
		bsmodel.setFailureCount(Long.parseLong(failureTriggerCount.toString()));
		bsmodel.setSuccessCount(Long.parseLong(successTroggerCount.toString()));
		bsmodel.setTotalCount(Long.parseLong(String.valueOf(failureTriggerCount+successTroggerCount)));
		batchService.saveBatchSummary(bsmodel);
		
	}
}
