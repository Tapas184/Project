package com.drl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drl.edervice.IEdService;
import com.drl.model.Indvinfo;
import com.drl.model.PlanInfo;

@RestController
public class EligibilityDeterminationContoller {

	@Autowired
	private IEdService edservice;
	
	@PostMapping("/checkPlan")
	public ResponseEntity<PlanInfo> getPlanUpdate(@RequestBody Indvinfo info){
		PlanInfo planInfo=null;
		try {
		 planInfo = edservice.determineEligibility(info);
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		return new ResponseEntity<>(planInfo,HttpStatus.OK);
		
	}
}
