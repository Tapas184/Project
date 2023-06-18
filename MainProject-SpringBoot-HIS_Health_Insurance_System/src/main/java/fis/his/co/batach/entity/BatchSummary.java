package fis.his.co.batach.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "HIS_BATCH_SUMMARY")
public class BatchSummary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6311030941696508111L;

	@Id
	@Column(name = "Sumry_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long summaryId;
	
	@Column(name = "Batch_name")
	private String batchName;
	
	@Column(name = "Failure_Count")
	private Long failureCount;
	
	@Column(name = "Success_Count")
	private Long successCount;
	
	@Column(name = "Total_Count")
	private Long totalCount;
	
}
