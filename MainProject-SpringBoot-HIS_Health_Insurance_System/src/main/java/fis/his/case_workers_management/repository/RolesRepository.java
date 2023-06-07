package fis.his.case_workers_management.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.case_workers_management.entity.EntityForRole;

public interface RolesRepository extends JpaRepository<EntityForRole, Serializable> {

}
