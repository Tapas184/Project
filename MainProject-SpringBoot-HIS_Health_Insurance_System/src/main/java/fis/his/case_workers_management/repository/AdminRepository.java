package fis.his.case_workers_management.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.case_workers_management.entity.EntityForAdmin;


public interface AdminRepository extends JpaRepository<EntityForAdmin, Serializable> {
	
	public  EntityForAdmin findByEmailid(String emailid);

}
