package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.StateMaster;
import java.util.List;


public interface StateRepository extends JpaRepository<StateMaster, Integer> {

	public List<StateMaster> findByCountryid(Integer countryid);
}
