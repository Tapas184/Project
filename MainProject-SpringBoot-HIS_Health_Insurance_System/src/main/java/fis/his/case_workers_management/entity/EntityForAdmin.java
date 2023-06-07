package fis.his.case_workers_management.entity;

import java.io.Serializable;
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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name = "CW_AND_ADMIN_MANAGEMENT_DB")
@SQLDelete(sql = "UPDATE CW_AND_ADMIN_MANAGEMENT_DB SET status=INACTIVE WHERE userid=?")
@Where(clause = "status!=INACTIVE")
public class EntityForAdmin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7678954304175803020L;

	
	/*
	 * @GeneratedValue(generator = "custom_generator",strategy =
	 * GenerationType.IDENTITY)
	 * 
	 * @GenericGenerator(name ="custom_generator", strategy =
	 * "fis.his.case_workers_management.customgenerator.CustomGenerator")
	 */
	@Id
	@SequenceGenerator(name = "Admin_seq",sequenceName = "HIS_SEQ",initialValue = 100,allocationSize = 1)
	@GeneratedValue(generator = "Admin_seq",strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_ID")
	private Integer userid;
	
	@Column(name = "FIRST_NAME",length = 20)
	private String fname;
	
	@Column(name = "LAST_NAME",length = 15)
	private String lname;
	
	@Column(name = "EMAIL_ID",length = 30,unique = true)
	private String emailid;
	
	@Column(name = "PHONE_NUMBER")
	private Long  phnumber;
	
	@Column(name = "GENDER",length = 1)
	private Character gender;
	
	@Column(name = "ROLE",length = 10)
	private String role;
	
	@Column(name = "PASS_WORD",length = 100)
	private String pwd;
	
	@Column(name = "STATUS",length = 10)
	private String status="LOCKED";
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INSERT_DATE", updatable = false)
	private Date insertdate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Update_Date", insertable = false)
	private Date updatedate;

}
