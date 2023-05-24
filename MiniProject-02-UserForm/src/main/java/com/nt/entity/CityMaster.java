package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CITY_MASTER")
public class CityMaster {
	
	@Id
	@Column(name = "CITY_ID")
	private Integer cityid;
	
	@Column(name = "STATE_ID")
	private Integer stateid;
	
	@Column(name = "CITY_NAME")
	private String cityname;
	

}
