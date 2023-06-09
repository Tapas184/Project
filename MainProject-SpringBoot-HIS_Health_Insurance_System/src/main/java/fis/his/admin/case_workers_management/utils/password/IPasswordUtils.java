package fis.his.admin.case_workers_management.utils.password;

public interface IPasswordUtils {
	
	public String encryption(String msg) throws Exception;
	
	public String decryption(String msg) throws Exception;

}
