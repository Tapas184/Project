package fis.his.admin.planmngmt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.admin.planmngmt.entity.PlanEntity;


public interface PlanRepository extends JpaRepository<PlanEntity, Serializable> {
	
	public PlanEntity findByPlanName(String planName);

}
