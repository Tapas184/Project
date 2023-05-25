package com.nt.entity;


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
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_REG")
@Entity
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 500L;

	@Id
	@SequenceGenerator(name = "user_seq",sequenceName = "user_seq_gen",initialValue = 100,allocationSize = 1)
	@GeneratedValue(generator = "user_seq",strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "First_Name", length = 20)
	private String fname;
	
	@Column(name = "Last_Name", length = 20)
	private String lname;
	
	@Column(name = "Mail_Id", length = 30,unique = true)
	private String email;
	
	@Column(name = "Phone_No",length = 20)
	private Long phno;
	
	@Column(name = "Gender", length = 10)
	private String gender;
	
	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "Status", length = 15)
	private String status="Locked";
	
	@Column(name = "User_PWD", length = 10)
	private String pwd;
	
	@Column(name = "Country_Id")
	private Integer countryid;
	
	@Column(name = "State_Id")
	private Integer stateid;
	
	@Column(name = "City_Id")
	private Integer cityid;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Insert_Date", updatable = false)
	private Date insertdate;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Update_Date", insertable = false)
	private Date updatedate;
}// end of the class
