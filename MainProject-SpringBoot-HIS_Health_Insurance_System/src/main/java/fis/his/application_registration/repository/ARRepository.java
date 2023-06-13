package fis.his.application_registration.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.his.application_registration.entity.AREntity;

public interface ARRepository extends JpaRepository<AREntity, Serializable> {

}
