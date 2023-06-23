package com.drools.service;

import com.drools.model.Personinfo;
import com.drools.model.Planinfo;

public interface IRule {
	
	public void loadRuleFile(String drlFilePath);
	
	public Planinfo processData(Personinfo info);

}
