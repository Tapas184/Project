package fis.his.application_registration.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
	
	@Column(name = "AR_CREATE_DATE")
	@DateTimeFormat(pattern = "dd-MM-YYYY")
	private LocalDate createdDate;
	
	@Column(name = "AP_LASTUPDATE_DATE")
	@DateTimeFormat(pattern = "dd-MM-YYYY")
	private LocalDate lastUpdate;
}
