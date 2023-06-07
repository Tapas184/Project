package fis.his.case_workers_management.service.adminandcw;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.filter.Filter;
import fis.his.case_workers_management.entity.EntityForAdmin;
import fis.his.case_workers_management.entity.EntityForCw;
import fis.his.case_workers_management.model.CwAndAdPojo;
import fis.his.case_workers_management.repository.AdminRepository;
import fis.his.case_workers_management.repository.CwRepository;
import fis.his.case_workers_management.utils.mail.MailInterface;
import fis.his.case_workers_management.utils.temppass.RandomPassGenerator;

@Service
public class AdminAndCWServiceImpl implements AdminAndCwServiceInterface {
	
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private CwRepository cwrRepository;
	
	@Autowired
	private MailInterface mail;
	@Autowired
	private Environment env;
	@Override
	public String checkMailAvailableOrNot(String email) {
        EntityForAdmin user = adminRepo.findByEmailid(email);
		if(user!=null)
			return "duplicate";
		return "unique";
	}

	@Override
	public String createNewAdminOrCw(CwAndAdPojo pojo){
		try {
		if(pojo.getRole().equalsIgnoreCase("admin")) {
			EntityForAdmin admin = new EntityForAdmin();
			BeanUtils.copyProperties(pojo, admin);
			admin.setPwd(RandomPassGenerator.temppassGen(Integer.parseInt(env.getProperty("temporarypassword.length"))));
			adminRepo.save(admin);
			BeanUtils.copyProperties(admin, pojo);
			mail.sendMail(pojo);
			if(pojo.getUserid()!= null) 
				return "Admin Id generated with :"+admin.getUserid()+",kindly check your mail and "
						+ "unlock your account";
			return "Admin Id :"+admin.getUserid()+" is successfully updated";
		}
		else if(pojo.getRole().equalsIgnoreCase("cw")) {
			EntityForCw cw = new EntityForCw();
			BeanUtils.copyProperties(pojo, cw);
			cw.setPwd(RandomPassGenerator.temppassGen(Integer.parseInt(env.getProperty("temporarypassword.length"))));
			cwrRepository.save(cw);
			BeanUtils.copyProperties(cw, pojo);
			mail.sendMail(pojo);
			if(pojo.getUserid()!=null)
				return "Case Workers id generatedSuccessFully Id is: "+cw.getUserid()+",Kindly check your mail"
						+ "for unlock your acocunt";
			return "Case workers id :"+cw.getUserid()+" successfully updated";
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public CwAndAdPojo getuser(String email) {
		EntityForAdmin admin = adminRepo.findByEmailid(email);
		CwAndAdPojo pojoAdmin = new CwAndAdPojo();
		BeanUtils.copyProperties(admin, pojoAdmin);
		return pojoAdmin;
	}
	
	@Override
	public String accountUpdate(CwAndAdPojo pojo) {
		EntityForAdmin user = adminRepo.findByEmailid(pojo.getEmailid());
		user.setPwd(pojo.getPwd());
		user.setStatus("ACTIVE");
		adminRepo.save(user);
		return "Congratulation your account now Active you can login ";
	}
	
	@Override
	public List<CwAndAdPojo> getAllData() {
		List<EntityForAdmin> adminList = adminRepo.findAll();
		List<CwAndAdPojo> pojoList = new ArrayList<>();
		adminList.stream()
		         .forEach(s->{
		        	 CwAndAdPojo p = new CwAndAdPojo();
		        	 BeanUtils.copyProperties(s, p);
		        	 pojoList.add(p);
		         });
		        
		return pojoList;
	}
}

