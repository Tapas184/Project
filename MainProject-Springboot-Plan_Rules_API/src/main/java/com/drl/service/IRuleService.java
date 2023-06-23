package com.drl.service;

import com.drl.model.Indvinfo;
import com.drl.model.PlanInfo;

public interface IRuleService {

	
	public PlanInfo executeRules(Indvinfo info);
}
