package fis.his.dc.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.dc.entity.DcPlanEntity;

public interface DcPlanRepository extends JpaRepository<DcPlanEntity, Serializable> {

}
