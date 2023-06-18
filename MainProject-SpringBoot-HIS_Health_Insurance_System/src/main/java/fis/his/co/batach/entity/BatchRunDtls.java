package fis.his.co.batach.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "HIS_Batch_Run_Details")
public class BatchRunDtls implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4402380057253717532L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Batch_Id")
	private Long batchRunId;
	
	@Column(name = "Batch_Name")
	private String batchName;
	
	@Column(name = "Batch_Status")
	private String batchRunStatus;
	
	@Column(name = "Batch_EndDate")
	private Date endDate;
	
	@Column(name = "Instance_No")
	private Integer instanceNumber;
	
	@Column(name = "Start_Date")
	private Date startDate;
	
}
