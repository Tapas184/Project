package fis.his.dc.ccap.model;

import lombok.Data;

@Data
public class CcapModel {

	

	private Long id;

	private String planName;

	private String marriedStatus;

	private String childStatus;

	private Integer childCount;

	private Integer childAge;

	private Integer applicationId;
	
	private Integer caseId;
}
