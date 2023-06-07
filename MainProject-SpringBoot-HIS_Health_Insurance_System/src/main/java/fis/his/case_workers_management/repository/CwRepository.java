package fis.his.case_workers_management.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.case_workers_management.entity.EntityForCw;

public interface CwRepository extends JpaRepository<EntityForCw, Serializable> {

	public  Optional<EntityForCw> findByEmailid(String emailid);
}
