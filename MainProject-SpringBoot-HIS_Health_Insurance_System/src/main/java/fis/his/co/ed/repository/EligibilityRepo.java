package fis.his.co.ed.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.co.ed.entity.EligibilityEntity;


public interface EligibilityRepo extends JpaRepository<EligibilityEntity, Serializable> {
	
	public EligibilityEntity findByCaseNumber(Long caseNumber);

}
