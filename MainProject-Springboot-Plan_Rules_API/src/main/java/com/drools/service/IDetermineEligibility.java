package com.drools.service;

import com.drools.model.Personinfo;
import com.drools.model.Planinfo;

public interface IDetermineEligibility {

	public Planinfo determineEligibility(Personinfo info);
}
