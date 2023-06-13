package fis.his.application_registration.service;

import java.util.List;

import fis.his.application_registration.model.ARModel;

public interface ARServiceInterface {
	
	public String createApplication(ARModel model);
	
	public List<ARModel> allApplication();
	
	public ARModel fetchApplication(Integer id);
}
