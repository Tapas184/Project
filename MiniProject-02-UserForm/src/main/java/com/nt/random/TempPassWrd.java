package com.nt.random;

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

}
