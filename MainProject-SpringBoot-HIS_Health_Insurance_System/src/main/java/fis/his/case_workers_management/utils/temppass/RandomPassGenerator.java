package fis.his.case_workers_management.utils.temppass;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class RandomPassGenerator {
	
	private RandomPassGenerator() {
		super();
	}

	public static final Random randam = new Random();
          private static     String str = UUID.randomUUID()
            		   .toString()
            		   .replace("-", "");

	public static String temppassGen(int length) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<length; i++) {
			sb.append(str.charAt(randam.nextInt(str.length()-1)));
		}
		return sb.toString();
	}
	
	public static int otp(int length) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<length;i++) {
			int nextInt = randam.nextInt(9);
			sb.append(nextInt);
		}
		return Integer.parseInt(sb.toString());
	}

	public static int otpGen() {
		return Integer.parseInt( new DecimalFormat("000000")
				.format(randam.nextInt(999999)));
	}
}
