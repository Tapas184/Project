package fis.his.application_registration.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import fis.his.dc.entity.DcPlanEntity;
import lombok.Data;

@Data
@Entity
@Table(name = "HIS_Application_Registration_DB")
public class AREntity {
	
	@Id
	@GeneratedValue(generator = "AR_SEQ",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "AR_SEQ",initialValue = 1000,allocationSize = 10,sequenceName = "AR_SEQ_GEN")
	@Column(name = "AR_ID")
	private Integer id;
	
	@Column(name = "AR_FNAME")
    private String fname;
	
	@Column(name = "AR_LNAME")
	private String lname;
	
	@Column(name = "AR_DOB")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dob;
	
	@Column(name = "AR_GENDER",length = 1)
	private Character gender;
	
	@Column(name = "AR_SSN",length = 15)
	private String ssn;
	
	@Column(name = "AR_PHNO")
	private String phone;
	
	@Column(name ="AR_MAILID",unique = true,length = 50)
	private String mail;
	
	@Column(name = "AR_STATE_NAME")
	private String stateName;
	
	@Column(name = "AR_VERIFY_STATUS")
	private String vrifyStatus;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE", updatable = false)
	private Date createdDate;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE_DATE", insertable = false)
	private Date lastUpdate;
}
