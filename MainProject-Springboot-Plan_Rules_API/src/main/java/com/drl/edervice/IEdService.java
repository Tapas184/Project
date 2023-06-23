package com.drl.edervice;

import com.drl.model.Indvinfo;
import com.drl.model.PlanInfo;

public interface IEdService {
	
	public PlanInfo determineEligibility(Indvinfo info);

}
