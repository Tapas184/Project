package fis.his.co.batach.model;

import lombok.Data;

@Data
public class BatchSumryModel {

	private Long summaryId;
	
	private String batchName;
	
	private Long failureCount;
	
	private Long successCount;
	
	private Long totalCount;
}
