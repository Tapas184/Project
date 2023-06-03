package fis.ssn.model;

import java.util.Date;

import lombok.Data;

@Data
public class SsnPojo {
	private Long ssn;
	private String fname;
	private String lname;
	private String gender;
	private Date dob;
	private String stateName;
}
