package fis.his.admin.planmngmt.service;

import java.util.List;

import fis.his.admin.planmngmt.model.PlanModel;

public interface PlanServiceInterface {
	
	public String newPlan(PlanModel plan);
	
	public List<PlanModel> getAllList();
	
	public boolean changeStatus(Integer id);
	
	public PlanModel getPlanDetails(Integer id);

}
