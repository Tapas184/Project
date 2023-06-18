package fis.his.co.ed.entity;

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
@Table(name = "HIS_Eligibility_Dtls")
public class EligibilityEntity implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6626511337281958426L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long edTraceId;
	
	@Column(name = "Benifit_Amount")
	private Integer benifitAmount;
	
	@Column(name = "Case_Id")
	private Long caseNumber;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE", updatable = false)
	private Date createdDate;
	
	@Column(name = "Denied_Coz")
	private String deniedReason;
	
	@Column(name = "Plan_Name")
	private String planName;
	
	@Column(name = "Plan_Start_Dt")
	private Date planStartDate;
	
	@Column(name = "Plan_End_Dt")
	private Date planEndDate;
	
	@Column(name = "Plan_Status")
	private String planStatus;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Update_DATE", insertable = false)
	private Date updateDate;
}
