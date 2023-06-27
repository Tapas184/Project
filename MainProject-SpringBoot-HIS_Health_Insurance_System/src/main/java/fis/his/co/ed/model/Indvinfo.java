package fis.his.co.ed.model;

import java.util.Date;

import lombok.Data;

@Data
public class Indvinfo {

	private String planName;

	private String fName;

	private String lName;

	private String isEmployee;

	private Integer salary;

	private PlanInfo planInfo;

	private String marriedStatus;

	private String childStatus;

	private Integer childCount;

	private Integer childAge;
	
	private Long caseNumber;
	
	private Date planStrtDate;
	private Date planEndDate;
	private Long traceId;

}
