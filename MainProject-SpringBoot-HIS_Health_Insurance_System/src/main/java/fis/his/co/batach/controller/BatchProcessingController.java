package fis.his.co.batach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fis.his.admin.case_workers_management.constant.LogConstant;
import fis.his.co.batach.process.BatchProcessing;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/batch")
@Slf4j
public class BatchProcessingController {

	@Autowired
	private BatchProcessing batch;
	
	@GetMapping("/pdf")
	public void getPdf(){
		log.info(LogConstant.METHOD_EXECUTION_STARTED+"-Batch pdf gen");
		try {
			log.debug("Debug started in pdf genration");
			batch.test();
			log.debug("pdf generation ended");
		} catch (NumberFormatException | ClassNotFoundException e) {
			log.error("Problem in pdf generation"+e.getMessage());
			e.printStackTrace();
		}
		log.info(LogConstant.METHOD_EXECUTION_ENDED);
	}
}
