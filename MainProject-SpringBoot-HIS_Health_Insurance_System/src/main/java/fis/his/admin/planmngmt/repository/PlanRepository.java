package fis.his.admin.planmngmt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.admin.planmngmt.entity.PlanEntity;
import java.util.List;


public interface PlanRepository extends JpaRepository<PlanEntity, Serializable> {
	
	public List<PlanEntity> findByPlanName(String planName);

}
