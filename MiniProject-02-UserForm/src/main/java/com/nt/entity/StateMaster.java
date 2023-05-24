package com.nt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STATE_MASTER")
public class StateMaster {

	@Id
	@Column(name = "STATE_ID")
	private Integer stateid;
	@Column(name = "COUNTRY_ID")
	private Integer countryid;
	@Column(name = "STATE_NAME")
	private String statename;
	
}
