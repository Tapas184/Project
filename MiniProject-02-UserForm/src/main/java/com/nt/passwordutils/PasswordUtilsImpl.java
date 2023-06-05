package com.nt.passwordutils;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class PasswordUtilsImpl implements IpasswordUtils {
	private static final String SECRET_KEY="ghrhdjfkrdjfkefh";
	private static final String INIT_VECTOR="gfjslkeislfhrjdj";
	@Override
	public String encryption(String msg) throws Exception {
			SecretKeySpec secrectSpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
		IvParameterSpec ivparamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());
		Cipher cpr = Cipher.getInstance("AES/CBC/NoPadding");
		   cpr.init(Cipher.ENCRYPT_MODE,secrectSpec, ivparamSpec);
		byte[] doFinal = cpr.doFinal(msg.getBytes());
		
		return Base64.getEncoder().encodeToString(doFinal);
	}

	@Override
	public String decryption(String msg) throws Exception {
		 SecretKeySpec secrestSpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
		 IvParameterSpec ivparamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());
		 Cipher cpr =Cipher.getInstance("AES/CBC/NoPadding");
		   cpr.init(Cipher.DECRYPT_MODE, secrestSpec,ivparamSpec);
		 byte[] decode = Base64.getDecoder().decode(msg);
		 byte[] doFinal = cpr.doFinal(decode);
		 return new String(doFinal);
	}

}
