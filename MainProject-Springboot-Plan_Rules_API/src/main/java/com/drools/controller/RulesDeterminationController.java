package com.drools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drools.model.Personinfo;
import com.drools.model.Planinfo;
import com.drools.service.IDetermineEligibility;

@RestController
public class RulesDeterminationController {

	@Autowired
	private IDetermineEligibility checkEligibility;
	
	@PostMapping("/checkeligibility")
	public ResponseEntity<Planinfo> getStatus(@RequestBody Personinfo info){
		Planinfo planinfo = checkEligibility.determineEligibility(info);
		return new ResponseEntity<>(planinfo, HttpStatus.OK);
	}
}
