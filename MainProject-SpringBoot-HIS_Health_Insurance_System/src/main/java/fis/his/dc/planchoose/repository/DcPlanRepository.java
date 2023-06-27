package fis.his.dc.planchoose.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.dc.planchoose.entity.DcPlanEntity;

public interface DcPlanRepository extends JpaRepository<DcPlanEntity, Serializable> {

}
