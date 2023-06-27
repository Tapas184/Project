package com.drools.service.impl;

import org.kie.api.builder.KieFileSystem;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drools.constants.DroolsConstants;
import com.drools.constants.LogMsg;
import com.drools.models.Indvinfo;
import com.drools.models.PlanInfo;
import com.drools.service.DroolsService;
import com.drools.utils.DroolsUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author navneetprabhakar
 */
@Service
@Slf4j
public class DroolsServiceImpl implements DroolsService {
    @Autowired
    private DroolsUtils droolsUtils;
    
    @Autowired
    private KieFileSystem kieFileSystem;
    /**
     * This method Will check eligibility from rules
     * @param : Indvinfo
     * @return : PlanInfo
     */
    @Override
    public PlanInfo checkEligibility(Indvinfo info) {
        log.info(LogMsg.METHOD_EXECUTION_STARTED+"-checkEligibility");
        String path = "/sample/"+info.getPlanName()+".drl";
        kieFileSystem.write(ResourceFactory.newClassPathResource(path));
         Indvinfo personInfo = droolsUtils.executeRuleEngine(info, DroolsConstants.SAMPLE.getSession());
         log.info(LogMsg.METHOD_EXECUTION_ENDED);
         return personInfo.getPlanInfo();
    }

}
