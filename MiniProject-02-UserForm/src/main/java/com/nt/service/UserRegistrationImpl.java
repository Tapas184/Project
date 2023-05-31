package com.nt.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.customerror.ErrorInUserRegistration;
import com.nt.customerror.ProblemInMailSend;
import com.nt.entity.CityMaster;
import com.nt.entity.CountryMaster;
import com.nt.entity.StateMaster;
import com.nt.entity.UserEntity;
import com.nt.random.TempPassWrd;
import com.nt.repository.CityRepository;
import com.nt.repository.CountryRepository;
import com.nt.repository.StateRepository;
import com.nt.repository.UserRegistrationRepo;
import com.nt.util.EmailUtils;

@Service("userService")
public class UserRegistrationImpl implements UserRegistrationInterface {

	@Autowired
	private UserRegistrationRepo repo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private EmailUtils email;

	/**
	 * This method used for get the update whether user is available or not
	 * 
	 * @return String
	 * @param String
	 */
	@Override
	public String userDetailsFindByMail(String mail) {
		UserEntity user = repo.findByEmail(mail);
		if (user != null) {
			return "duplicate";
		}
		return "unique";
	}

	/**
	 * Method for Save User in data base
	 * 
	 * @return: String
	 * @param : UserObject
	 */
	@Override
	public String userInsert(UserEntity user) throws ErrorInUserRegistration, ProblemInMailSend {
		Integer uid = 0;
		// for insert
		try {
			if (user.getId() == null) {
				user.setPwd(TempPassWrd.genTempPass(6));
				uid = repo.save(user).getId();
				boolean sendMail = email.sendMail(user);
				if (sendMail)
					return "User almost registered, Kindly set passwored for your account id is," + uid + "<br>"
							+ "Kinly check your mail and and activate your account";
				else
					throw new ProblemInMailSend("Problem In Mail send");
			}
			// for update
			else {
				uid = repo.save(user).getId();
				return user.getFname() + " Is updated sucessfully Id :" + uid;
			}
		} catch (Exception e) {
			throw new ErrorInUserRegistration(e.getMessage());
		}
	}// end of the method ErrorInUserRegistration

	@Override
	public Map<Integer, String> getCountrisInfo() {
		// get All Countries
		List<CountryMaster> countryList = countryRepo.findAll();
		Map<Integer, String> map = new LinkedHashMap<>();
		// convert countrylist to Map Object
		countryList.forEach(s -> map.put(s.getId(), s.getCountryname()));
		return map;
	}//

	@Override
	public Map<Integer, String> getStateInfo(Integer countryId) {
		// call the StateRepositry method
		List<StateMaster> stateList = stateRepo.findByCountryid(countryId);
		Map<Integer, String> map = new LinkedHashMap<>();
		// convert List collection to map
		stateList.forEach(state -> map.put(state.getStateid(), state.getStatename()));
		return map;
	}// stateInfo

	@Override
	public Map<Integer, String> getCityInfo(Integer stateId) {
		// call the city repository method
		List<CityMaster> cityList = cityRepo.findByStateid(stateId);
		Map<Integer, String> map = new LinkedHashMap<>();
		// convert city list into map
		cityList.forEach(city -> map.put(city.getCityid(), city.getCityname()));
		return map;
	}// city info
	
	@Override
	public UserEntity findUserByMail(String mail) {
		return repo.findByEmail(mail);
	}
}
