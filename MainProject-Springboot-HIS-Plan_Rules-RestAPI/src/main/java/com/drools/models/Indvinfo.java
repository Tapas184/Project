package com.drools.models;

import lombok.Data;

@Data
public class Indvinfo  {
	
	private String planName;
	
	private String fName;
	
	private String lName;
	
	private String isEmployee;
	
	private Integer salary;
	
	private PlanInfo planInfo;
}
