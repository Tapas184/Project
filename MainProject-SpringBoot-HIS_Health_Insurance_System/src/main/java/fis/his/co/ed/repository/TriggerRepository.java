package fis.his.co.ed.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.co.ed.entity.CoTriggerEntity;

public interface TriggerRepository extends JpaRepository<CoTriggerEntity, Serializable> {

}
