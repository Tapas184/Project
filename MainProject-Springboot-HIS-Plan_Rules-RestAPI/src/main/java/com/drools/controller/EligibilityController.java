package com.drools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drools.constants.LogMsg;
import com.drools.models.Indvinfo;
import com.drools.models.PlanInfo;
import com.drools.service.DroolsService;

import lombok.extern.slf4j.Slf4j;

/** This res controller class to handle request from end HIS application 
 * @author Tapas Rout
 * Sample Drools Rule controller
 */
@RestController
@Slf4j
public class EligibilityController {

    @Autowired
    private DroolsService droolsService;


    /**
     * This POST API calculates total marks, percentage and passing grade based on the input provided
     * @param request
     * @return
     */
    @PostMapping("/checkplan")
    public PlanInfo planCheckEligibility(@RequestBody Indvinfo info) {
    	log.info(LogMsg.METHOD_EXECUTION_STARTED+"-planCheckEligibility");
        return droolsService.checkEligibility(info);
    }

}
