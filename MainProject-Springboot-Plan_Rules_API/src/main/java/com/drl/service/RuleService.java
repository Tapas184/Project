package com.drools.service;

import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Service;

import com.drools.model.Personinfo;
import com.drools.model.Planinfo;

@Service
public class RuleService implements IRule{

	private final KieContainer container;

	public RuleService(KieContainer container) {
		this.container=container;
	}
	@Override
	public void loadRuleFile(String drlFilePath) {
		 KieServices kieServices = KieServices.Factory.get();
		  Resource resource = ResourceFactory.newClassPathResource(drlFilePath);
		  KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		  kieFileSystem.write(resource);
		  kieServices.newKieBuilder(kieFileSystem)
		             .buildAll();
		  
	}
	@Override
	public Planinfo processData(Personinfo info) {
		Planinfo planinfo=null;
		try {
		KieSession kieSession = container.newKieSession();
		kieSession.insert(info);
		kieSession.fireAllRules();
		planinfo= info.getPlaninfo();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return planinfo;
	}
}
