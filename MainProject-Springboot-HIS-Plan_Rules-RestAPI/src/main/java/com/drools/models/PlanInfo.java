package com.drools.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "PlanInf model for response Object")
public class PlanInfo {

	@ApiModelProperty(name = "This is for plan name",example = "String")
	private String planName;
	@ApiModelProperty(name = "This is for planStatus",example = "String")	
	private String planStatus;
	@ApiModelProperty(name = "This is for update message",example = "String")
	private String msg;
	
}
