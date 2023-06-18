package fis.his.co.batach.service;

import fis.his.co.batach.model.BatchRunModel;
import fis.his.co.batach.model.BatchSumryModel;

public interface BatchServiceInterface {

	public BatchRunModel getBatchRun(Long id);
	
	public BatchSumryModel getbatchSumry(Long id);
	
	public BatchRunModel insertIntoBatchRun(BatchRunModel model);
	
	public BatchRunModel updateBatchRunDetails(BatchRunModel model);
	
	public void saveBatchSummary(BatchSumryModel model);
}
