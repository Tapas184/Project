package com.drools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.drools.models.Indvinfo;
import com.drools.models.PlanInfo;
import com.drools.service.DroolsService;

/** This res controller class to handle request from end HIS application 
 * @author Tapas Rout
 * Sample Drools Rule controller
 */
@RestController
public class EligibilityController {

    @Autowired
    private DroolsService droolsService;


    /**
     * This POST API calculates total marks, percentage and passing grade based on the input provided
     * @param request
     * @return
     */
    @PostMapping("/checkplan")
    public PlanInfo checkEligibility(@RequestBody Indvinfo info) {
        return droolsService.checkEligibility(info);
    }

}
