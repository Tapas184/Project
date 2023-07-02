package fis.his.dc.ccap.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.dc.ccap.entity.CcapEntity;

public interface CcapRepository extends JpaRepository<CcapEntity, Serializable> {

}
