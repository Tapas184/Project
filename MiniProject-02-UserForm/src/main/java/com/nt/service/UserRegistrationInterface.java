package com.nt.service;

import java.util.Map;

import com.nt.entity.UserEntity;

public interface UserRegistrationInterface {
	
	
	public String userDetailsFindByMail(String mail);
	
	public String userInsert(UserEntity user);
	
	public Map<Integer, String> getCountrisInfo();
	
	public Map<Integer, String> getStateInfo(Integer countryId);
	
	public Map<Integer, String> getCityInfo(Integer stateId);
	
	public UserEntity findUserByMail(String mail);
	

}
