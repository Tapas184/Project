package fis.his.dc.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import fis.his.admin.planmngmt.entity.PlanEntity;
import fis.his.application_registration.entity.AREntity;
import lombok.Data;

@Entity
@Data
@Table(name = "HIS_DC_PLAN_DB")
public class DcPlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PLAN_ID")
	private Long planid;
		
	@Column(name = "USER_FNAME")
	private String fname;
	
	@Column(name = "USER_LNAME")
	private String lname;
	
	private String plan;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE", updatable = false)
	private Date createdDate;
	@Column(name = "USER_AR_ID")
	private Integer id;
}
