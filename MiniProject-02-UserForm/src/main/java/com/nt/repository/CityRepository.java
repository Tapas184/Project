package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CityMaster;
import java.util.List;


public interface CityRepository extends JpaRepository<CityMaster, Integer> {

	public List<CityMaster> findByStateid(Integer stateid);
}
