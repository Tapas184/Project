package com.drools.service;

import com.drools.models.Indvinfo;
import com.drools.models.PlanInfo;

/**
 * Service Interface for Drools Related Service APIs
 * @author Tapas Rout
 *
 */
public interface DroolsService {

    /**
     * This method calculates DroolsResponse based on the request and the rules written for that session
     * @param request
     * @return
     */
    PlanInfo checkEligibility(Indvinfo info);
}
