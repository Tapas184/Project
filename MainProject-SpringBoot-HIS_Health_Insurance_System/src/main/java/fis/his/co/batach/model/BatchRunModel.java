package fis.his.co.batach.model;

import java.util.Date;

import lombok.Data;

@Data
public class BatchRunModel {

	private Long batchRunId;
	
	private String batchName;
	
	private String batchRunStatus;
	
	private Date endDate;
	
	private Long instanceNumber;

	private Date startDate;
}
