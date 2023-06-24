package com.drools.service.impl;

import org.kie.api.builder.KieFileSystem;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drools.constants.DroolsConstants;
import com.drools.models.Indvinfo;
import com.drools.models.PlanInfo;
import com.drools.service.DroolsService;
import com.drools.utils.DroolsUtils;

import lombok.extern.log4j.Log4j2;

/**
 * @author navneetprabhakar
 */
@Service
@Log4j2
public class DroolsServiceImpl implements DroolsService {
    @Autowired
    private DroolsUtils droolsUtils;
    
    @Autowired
    private KieFileSystem kieFileSystem;
    /**
     * This method calculates DroolsResponse based on the request and the rules written for that session
     * @param request
     * @return
     */
    @Override
    public PlanInfo checkEligibility(Indvinfo info) {
        log.info("initiating calculate result");
        String path = "/sample/"+info.getPlanName()+".drl";
        kieFileSystem.write(ResourceFactory.newClassPathResource(path));
         Indvinfo personInfo = droolsUtils.executeRuleEngine(info, DroolsConstants.SAMPLE.getSession());
         return personInfo.getPlanInfo();
    }

}
