package com.drools.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drools.model.Personinfo;
import com.drools.model.Planinfo;

@Service
public class DetermineEligibilityService implements IDetermineEligibility {
	
	@Autowired
	private IRule ruleService;
	
	@Override
	public Planinfo determineEligibility(Personinfo info) {
		 String planName = info.getPlanName();
		 String path = "static.com.rules."+planName+".drl";
		 ruleService.loadRuleFile(path);
		 return ruleService.processData(info);
	}

}
