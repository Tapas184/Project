package com.drl.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.drools.core.rule.Package;
import org.springframework.stereotype.Service;

import com.drl.model.Indvinfo;
import com.drl.model.PlanInfo;

@Service
public class SNAPService implements IRuleService {


	@Override
	public PlanInfo executeRules(Indvinfo info){
		PlanInfo planInfo = null;
		try {
			//load the drl file
			InputStream inputStream = getClass().getResourceAsStream("/com/rules/SNAP.drl");
			Reader reader = new InputStreamReader(inputStream);
			
			
			PackageBuilder builder = new PackageBuilder();
			builder.addPackageFromDrl(reader);
			Package package1 = builder.getPackage();
			
			RuleBase ruleBase = RuleBaseFactory.newRuleBase();
			ruleBase.addPackage(package1);
			
			//firing rules
			WorkingMemory memory = ruleBase.newStatefulSession();
			memory.insert(info);
			memory.fireAllRules();
			planInfo = info.getPlanInfo();
			
		} catch (Exception  e) {
			
		       e.printStackTrace();
		}

		return planInfo;
	}

}
