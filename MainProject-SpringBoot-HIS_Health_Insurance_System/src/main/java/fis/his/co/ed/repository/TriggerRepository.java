package fis.his.co.ed.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fis.his.co.ed.entity.CoTriggerEntity;

public interface TriggerRepository extends JpaRepository<CoTriggerEntity, Serializable> {
	
	//Query for separate total data to in bucket for Parallel Instance of JVM
	@Query("FROM CoTriggerEntity WHERE trigStatue='P' AND ora_hash(triggerId,:bucketSize)=:bucketNumber")
	public List<CoTriggerEntity> findByBucket(int bucketSize,int bucketNumber);

}
