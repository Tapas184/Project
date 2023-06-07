package fis.his.case_workers_management.utils.password;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class PasswordUtilsImpl implements IPasswordUtils {
	@Override
	public String decryption(String msg) throws Exception {
		SecureRandom random = new SecureRandom();
		byte[] bytesIV = new byte[16];
		random.nextBytes(bytesIV);
		GCMParameterSpec iv = new GCMParameterSpec(128, bytesIV);
		SecretKeySpec skeySpec = new SecretKeySpec(msg.getBytes(StandardCharsets.UTF_8), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

		byte[] doFinal = cipher.doFinal(msg.getBytes());

		return Base64.getEncoder().encodeToString(doFinal);
	}

	@Override
	public String encryption(String msg) throws Exception {
		SecureRandom random = new SecureRandom();
		byte[] bytesIV = new byte[16];
		random.nextBytes(bytesIV);
		GCMParameterSpec iv = new GCMParameterSpec(128, bytesIV);
		SecretKeySpec skeySpec = new SecretKeySpec(msg.getBytes(StandardCharsets.UTF_8), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

		byte[] decode = Base64.getDecoder().decode(msg);
		byte[] doFinal = cipher.doFinal(decode);
		return new String(doFinal);
	}
}
