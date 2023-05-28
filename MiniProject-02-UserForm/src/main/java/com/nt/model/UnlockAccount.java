package com.nt.model;

import lombok.Data;

@Data
public class UnlockAccount {

	private String email;
	private String temPwd;
	private String newPwd;
	private String confrmPwd;
}// unlock account class
