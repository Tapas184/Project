package fis.ssn.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fis.ssn.entity.SsnState;

public interface SsnStateRepository extends JpaRepository<SsnState, Serializable> {
	
	@Query(value = "SELECT stateName FROM SsnState")
	public List<String> findAllState();

}
