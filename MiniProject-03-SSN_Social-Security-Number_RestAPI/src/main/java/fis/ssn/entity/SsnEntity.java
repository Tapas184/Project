package fis.ssn.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@Table(name = "SSN_TABLE")
@Entity
public class SsnEntity {

	@Id
	//@GenericGenerator(name = "cust_gen", strategy = "fis.ssn.customgenerator.CustomGenerator")
	@SequenceGenerator(name = "SSN_SEQ",sequenceName = "SSN_SEQ_GEN",initialValue = 900850000,allocationSize = 1)
	@GeneratedValue(generator = "SSN_SEQ",strategy = GenerationType.SEQUENCE)
	private Long ssn;
	@Column(name = "FIRST_NAME")
	private String fname;
	@Column(name = "LAST_NAME")
	private String lname;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "DOB")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	@Column(name = "STATE_NAME")
	private String stateName;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Insert_Date", updatable = false)
	private Date insertDate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Update_Date", insertable = false)
	private Date updateDate;
}
