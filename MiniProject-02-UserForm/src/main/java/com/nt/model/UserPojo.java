package com.nt.model;


import java.util.Date;

import lombok.Data;

@Data
public class UserPojo {
	
	private Integer id;
	private String fname;
	private String lname;
	private String email;
	private Long phno;
	private String gender;
	private String status = "LOCKED";
	private String pwd;
	private Date dob;
	private Integer countryid;
	private Integer stateid;
	private Integer cityid;
	private Date insertdate;
	private Date updatedate;

}
