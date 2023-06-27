package fis.his.dc.snap.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "HIS_DC_SNAP_TABLE")
public class SnapEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6392824308779182696L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long snapId;
	
	@Column(name = "IS_Employee")
	private String isEmployee;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Created_Date",updatable = false)
	private Date createdDate;
	
	@Column(name = "Salary")
	private Integer salary;
	
	@Column(name = "Case_Id")
	private Long caseId;
	
	@Column(unique = true, name = "Applica_ID")
	private Integer applicationId;
	
	@Column(name = "Plan_Name")
	private String planName;
}
