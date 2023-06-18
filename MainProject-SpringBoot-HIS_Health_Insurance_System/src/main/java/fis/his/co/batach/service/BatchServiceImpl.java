package fis.his.co.batach.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.co.batach.entity.BatchRunDtls;
import fis.his.co.batach.entity.BatchSummary;
import fis.his.co.batach.model.BatchRunModel;
import fis.his.co.batach.model.BatchSumryModel;
import fis.his.co.batach.repository.BatchRunRepository;
import fis.his.co.batach.repository.BatchSumryRepository;

@Service
public class BatchServiceImpl implements BatchServiceInterface {

	@Autowired
	private BatchRunRepository batchRunRepo;

	@Autowired
	private BatchSumryRepository batchSumryRepo;

	@Override
	public BatchRunModel getBatchRun(Long id) {
		Optional<BatchRunDtls> opt = batchRunRepo.findById(id);
		if (opt.isPresent()) {
			BatchRunDtls entity = opt.get();
			BatchRunModel model = new BatchRunModel();
			BeanUtils.copyProperties(entity, model);
			return model;
		}
		return null;
	}

	@Override
	public BatchSumryModel getbatchSumry(Long id) {
		Optional<BatchSummary> opt = batchSumryRepo.findById(id);
		if (opt.isPresent()) {
			BatchSummary entity = opt.get();
			BatchSumryModel model = new BatchSumryModel();
			BeanUtils.copyProperties(entity, model);
			return model;
		}
		return null;
	}

	@Override
	public BatchRunModel insertIntoBatchRun(BatchRunModel model) {
		BatchRunDtls entity = new BatchRunDtls();
		BeanUtils.copyProperties(model, entity);
		BatchRunDtls save = batchRunRepo.save(entity);
		model.setBatchRunId(save.getBatchRunId());
		return model;
	}
	
	@Override
	public BatchRunModel updateBatchRunDetails(BatchRunModel model) {
		BatchRunDtls entity = new BatchRunDtls();
		BeanUtils.copyProperties(model, entity);
		batchRunRepo.save(entity);
		return model;
	}
	
	@Override
	public void saveBatchSummary(BatchSumryModel model) {
		BatchSummary entity = new BatchSummary();
		BeanUtils.copyProperties(model, entity);
		batchSumryRepo.save(entity);
	}
}
