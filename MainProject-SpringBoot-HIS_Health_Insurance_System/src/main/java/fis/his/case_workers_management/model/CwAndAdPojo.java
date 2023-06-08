package fis.his.case_workers_management.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CwAndAdPojo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7448631672435142959L;

	private Integer userid;
	
	private String fname;
	
	private String lname;
	
	private String emailid;
	
	private Long  phnumber;
	
	private Character gender;
	
	private String role;
	
	private String pwd;
	
	private String status;
}
