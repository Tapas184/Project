package com.drools.model;

import lombok.Data;

@Data
public class Personinfo {
	
	private String isEmployed;
	
	private Integer salary;
	
	private String planName;
	
	private Planinfo planinfo;

	private Personinfo(String isEmployed, Integer salary) {
		super();
		this.isEmployed = isEmployed;
		this.salary = salary;
	}
}
