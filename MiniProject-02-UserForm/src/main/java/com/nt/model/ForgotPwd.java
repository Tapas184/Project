package com.nt.model;

import lombok.Data;

@Data
public class ForgotPwd {
	
	private String email;
	private String otp;
	private String newPass;
	private String confPass;
	

}//ForgotPwd
