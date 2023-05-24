package com.nt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CountryMaster;

public interface CountryRepository extends JpaRepository<CountryMaster, Serializable> {

}
