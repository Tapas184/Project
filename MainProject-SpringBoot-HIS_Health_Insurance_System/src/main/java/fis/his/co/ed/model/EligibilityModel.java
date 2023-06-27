package fis.his.co.ed.model;

import java.util.Date;

import lombok.Data;

@Data
public class EligibilityModel {

	private Long edTraceId;
	
	private Integer benifitAmount;
	
	private Long caseNumber;
	
	private String msg;
	
	private Date planStartDate;
	
	private Date planEndDate;
	
	private String planStatus;
	
	private String planName;

}
