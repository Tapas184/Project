package fis.ssn.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import fis.ssn.entity.SsnEntity;


public interface SsnRepository extends JpaRepository<SsnEntity, Serializable> {
}
