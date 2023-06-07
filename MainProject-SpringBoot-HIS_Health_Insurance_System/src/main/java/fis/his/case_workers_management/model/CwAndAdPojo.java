package fis.his.case_workers_management.model;

import lombok.Data;

@Data
public class CwAndAdPojo {

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
