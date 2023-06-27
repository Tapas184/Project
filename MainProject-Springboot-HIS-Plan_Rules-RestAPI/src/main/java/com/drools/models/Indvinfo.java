package com.drools.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Indvinfo model",subTypes = PlanInfo.class)
public class Indvinfo  {
	
	@ApiModelProperty(notes = " This property planName",example = "String",required = true)
	private String planName;
	
	@ApiModelProperty(notes = " This property userFirstname",example = "String")
	private String fName;
	
	@ApiModelProperty(notes = " This property LastName",example = "String")
	private String lName;
	
	@ApiModelProperty(notes = "This is for user is employee or not",example = "String")
	private String isEmployee;
	
	@ApiModelProperty(notes = " This property Salary",example = "Integer")
	private Integer salary;
	
	@ApiModelProperty(notes = " This property PlanInfo")
	private PlanInfo planInfo;
	
	@ApiModelProperty(notes = " This property marriedStatus",example = "String")
	private String marriedStatus;
	
	@ApiModelProperty(notes = " This property childStatus",example = "String")
	private String childStatus;
	
	@ApiModelProperty(notes = " This property ChildHeadCount",example = "Integer")
	private Integer childCount;
	
	@ApiModelProperty(notes = " This property ChildAge",example = "Integer")
	private Integer childAge;
	
	
}
