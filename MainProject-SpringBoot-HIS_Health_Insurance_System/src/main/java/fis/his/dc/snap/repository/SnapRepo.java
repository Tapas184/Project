package fis.his.dc.snap.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.dc.snap.entity.SnapEntity;

public interface SnapRepo extends JpaRepository<SnapEntity, Serializable> {

}
