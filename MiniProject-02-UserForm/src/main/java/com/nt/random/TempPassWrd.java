package com.nt.random;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class TempPassWrd {
	private static String str = UUID.randomUUID()
			                  .toString()
			                  .replace("-", "");
	public static String genTempPass(int length) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<length; i++) {
			sb.append(str.charAt(new Random().nextInt(str.length()-1)));
		}
		return sb.toString();
	}
	
	public static String otpGen() {
		StringBuilder otp = new StringBuilder();
		
		for(int i = 0; i<=5; i++) {
			otp.append(new Random().nextInt(9));
		}
		return otp.toString();
	}
	
	public static String otp() {
		return new DecimalFormat("000000")
				  .format(new Random().nextInt(999999));
	}

}
