package fis.his.co.batach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fis.his.co.batach.process.BatchProcessing;

@RestController
@RequestMapping("/batch")
public class BatchProcessingController {

	@Autowired
	private BatchProcessing batch;
	
	@GetMapping("/pdf")
	public void getPdf() {
		batch.test();
	}
}
