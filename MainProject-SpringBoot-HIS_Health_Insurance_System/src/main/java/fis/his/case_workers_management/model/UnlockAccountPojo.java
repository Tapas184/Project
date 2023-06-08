package fis.his.case_workers_management.model;

import lombok.Data;

@Data
public class UnlockAccountPojo {

	private String mail;
	private String name;
	private String newpassword;
	private String confpassword;
	private String tempass;
	private String otp;
}
