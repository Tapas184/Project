package com.drl.edervice;

import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.drl.model.Indvinfo;
import com.drl.model.PlanInfo;

@Service
public class EligibilityDetermineService implements IEdService {

	@Override
	public PlanInfo determineEligibility(Indvinfo info) {

		String planName = info.getPlanName();
		String clazName = "com.drl.service." + planName + "Service";
		PlanInfo pinfo = null;
		try {
			Class<?> clz = Class.forName(clazName);
			Method method = clz.getDeclaredMethod("executeRules", Indvinfo.class);
			Object object = clz.newInstance();

			Object resObj = method.invoke(object,info);
			 pinfo = (PlanInfo)resObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pinfo;
	}

}
