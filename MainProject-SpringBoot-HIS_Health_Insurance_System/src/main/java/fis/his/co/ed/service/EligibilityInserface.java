package fis.his.co.ed.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import fis.his.co.ed.model.EligibilityModel;
import fis.his.co.ed.model.Indvinfo;
import fis.his.co.ed.model.TriggerModel;

public interface EligibilityInserface {

	public EligibilityModel get(Long id);
	
	public TriggerModel getTrigger(Long id);
	
	public List<TriggerModel> fetchDataWhereStatusIsPendig();
	
	public EligibilityModel fetchByCaseNumber(Long caseno);
	
	public Boolean updateTrigger(TriggerModel trigModel);
	
	public List<TriggerModel> findByBucket(int bucketSize, int bucketNumber); 
	
	public void saveEligibilityData(Indvinfo info) throws JsonProcessingException;
}
