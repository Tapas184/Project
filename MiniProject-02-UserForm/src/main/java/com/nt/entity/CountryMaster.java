package com.nt.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "COUNTRY_MASTER")
public class CountryMaster implements Serializable {

	@Id
	@Column(name = "COUNTRY_ID")
	private Integer id;
	@Column(name = "COUNTRY_NAME")
	private String countryname;
}
