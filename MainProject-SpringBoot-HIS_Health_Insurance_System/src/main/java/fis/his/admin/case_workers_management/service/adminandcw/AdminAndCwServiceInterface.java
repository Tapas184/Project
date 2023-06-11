package fis.his.admin.case_workers_management.service.adminandcw;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fis.his.admin.case_workers_management.customexception.ExceptionInAccountActive;
import fis.his.admin.case_workers_management.customexception.ExceptionInMailSending;
import fis.his.admin.case_workers_management.customexception.ExceptionInSetAccountInactive;
import fis.his.admin.case_workers_management.entity.EntityForAdmin;
import fis.his.admin.case_workers_management.model.CwAndAdPojo;

public interface AdminAndCwServiceInterface {
	
	public String checkMailAvailableOrNot(String email);
	
	public String createNewAdminOrCw(CwAndAdPojo pojo);
	
	public CwAndAdPojo getuser(String email);
	
	public String accountUpdate(CwAndAdPojo pojo);
	
	public List<CwAndAdPojo> getAllData();
	
	public CwAndAdPojo getUserById(Integer id);
	
	public String postEditAccountUpdate(CwAndAdPojo pojo);
	
	public String unlockAccount(Integer id) throws ExceptionInMailSending;
	
	public String accountSetInactive(Integer id) throws ExceptionInSetAccountInactive;
	
	public String accountActivate(Integer id) throws ExceptionInAccountActive;
	
	public Boolean resetpassword(CwAndAdPojo pojo) throws IllegalAccessException;
	
	public Page<EntityForAdmin> findAllDetails(Pageable page );

}
