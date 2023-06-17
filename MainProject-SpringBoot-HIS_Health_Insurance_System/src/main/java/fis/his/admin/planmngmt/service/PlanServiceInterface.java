package fis.his.admin.planmngmt.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fis.his.admin.planmngmt.entity.PlanEntity;
import fis.his.admin.planmngmt.model.PlanModel;

public interface PlanServiceInterface {
	
	public String newPlan(PlanModel plan);
	
	public List<PlanModel> getAllList();
	
	public boolean changeStatus(Integer id);
	
	public PlanModel getPlanDetails(Integer id);
	
	public Page<PlanEntity> findAllPlan(Pageable pageable);
	
	public PlanEntity getByname(String name);


}
