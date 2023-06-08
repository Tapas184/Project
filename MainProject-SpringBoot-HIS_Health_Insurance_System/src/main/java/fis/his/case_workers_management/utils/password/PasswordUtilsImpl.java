package fis.his.case_workers_management.utils.password;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class PasswordUtilsImpl implements IPasswordUtils {
	
	private static final String SECRET_KEY=UUID.randomUUID().toString().substring(0, 16);
	private static final String INIT_VECTOR=UUID.randomUUID().toString().substring(0, 16);
	@Override
	public String decryption(String msg) throws Exception {
		SecretKeySpec secrestSpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
		 IvParameterSpec ivparamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());    
		Cipher cpr =Cipher.getInstance("AES/CBC/PKCS5Padding");
		   cpr.init(Cipher.DECRYPT_MODE, secrestSpec,ivparamSpec);
		 byte[] decode = Base64.getDecoder().decode(msg);
		 byte[] doFinal = cpr.doFinal(decode);
		 return new String(doFinal);
	}

	@Override
	public String encryption(String msg) throws Exception {
		SecretKeySpec secrectSpec = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
		IvParameterSpec ivparamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());
				
			Cipher cpr = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    
		   cpr.init(Cipher.ENCRYPT_MODE,secrectSpec, ivparamSpec);
		byte[] doFinal = cpr.doFinal(msg.getBytes());
		
		return Base64.getEncoder().encodeToString(doFinal);
	}
}
