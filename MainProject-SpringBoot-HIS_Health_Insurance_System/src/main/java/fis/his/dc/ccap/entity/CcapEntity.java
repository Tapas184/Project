package fis.his.dc.ccap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "HIS_CCAP_PLAN_DB")
public class CcapEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -570985616135914204L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "Plan")
	private String planName;
	
	@Column(name = "Married_Status")
	private String marriedStatus;
	
	@Column(name = "Child_Status")
	private String childStatus;
	
	@Column(name = "Child_Count")
	private Integer childCount;
	
	@Column(name = "Child_Age")
	private Integer childAge;
	
	@Column(name = "Application_Id")
	private Integer applicationId;
	
	@Column(name = "Case_Number")
	private Integer caseId;
	
	
}
