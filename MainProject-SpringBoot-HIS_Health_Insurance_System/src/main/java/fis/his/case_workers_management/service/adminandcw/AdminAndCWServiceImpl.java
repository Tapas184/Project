package fis.his.case_workers_management.service.adminandcw;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

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
	}//
	
	@Override
	public CwAndAdPojo getUserById(Integer id) {
		Optional<EntityForAdmin> user = adminRepo.findById(id);
		if(user.isPresent()) {
			CwAndAdPojo pojo= new CwAndAdPojo();
			BeanUtils.copyProperties(user.get(), pojo);
			return pojo;
		}	
		return null;
	}
	
	@Override
	public String postEditAccountUpdate(CwAndAdPojo pojo) {
		 Optional<EntityForAdmin> user = adminRepo.findById(pojo.getUserid());
		 if(user.isPresent()) {
			 EntityForAdmin admin = user.get();
			 admin.setFname(pojo.getFname());
			 admin.setLname(pojo.getLname());
			 admin.setPhnumber(pojo.getPhnumber());
			 admin.setRole(pojo.getRole());
			 adminRepo.save(admin);
			 return admin.getUserid()+"Acount updated successfully";
		 }
		 
		return null;
	}
	
	@Override
	public String unlockAccount(Integer id) throws Exception {
		Optional<EntityForAdmin> opt = adminRepo.findById(id);
		if(opt.isPresent()) {
			EntityForAdmin user = opt.get();
			user.setPwd(RandomPassGenerator.temppassGen(Integer.parseInt(env.getProperty("temporarypassword.length"))));
			adminRepo.save(user);
			CwAndAdPojo pojo = new CwAndAdPojo();
			BeanUtils.copyProperties(user, pojo);
			boolean sendMail = mail.sendMail(pojo);
			if(sendMail)
				return "Mail sent to registed email, please visit the mail and unlock the account";	
		}
		return null;
	}
}

