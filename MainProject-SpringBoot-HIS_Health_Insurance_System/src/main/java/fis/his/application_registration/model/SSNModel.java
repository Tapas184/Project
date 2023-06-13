package fis.his.application_registration.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class SSNModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5538921930744756227L;
	private Long ssn;
	private String fname;
	private String lname;
	private String gender;
	private Date dob;
	private String stateName;
}
