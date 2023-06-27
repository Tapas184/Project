package fis.his.co.ed.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fis.his.co.ed.entity.CoTriggerEntity;
import fis.his.co.ed.entity.EligibilityEntity;
import fis.his.co.ed.model.EligibilityModel;
import fis.his.co.ed.model.Indvinfo;
import fis.his.co.ed.model.PlanInfo;
import fis.his.co.ed.model.TriggerModel;
import fis.his.co.ed.repository.EligibilityRepo;
import fis.his.co.ed.repository.TriggerRepository;

@Service
public class EligibilityServiceImpl implements EligibilityInserface {

	@Autowired
	private EligibilityRepo erepo;

	@Autowired
	private TriggerRepository trepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ObjectMapper mapper;

	@Override
	public EligibilityModel get(Long id) {
		Optional<EligibilityEntity> opt = erepo.findById(id);
		if (opt.isPresent()) {
			EligibilityEntity entity = opt.get();
			EligibilityModel model = new EligibilityModel();
			BeanUtils.copyProperties(entity, model);
			return model;
		}
		return null;
	}

	@Override
	public TriggerModel getTrigger(Long id) {
		Optional<CoTriggerEntity> opt = trepo.findById(id);
		if (opt.isPresent()) {
			CoTriggerEntity entity = opt.get();
			TriggerModel model = new TriggerModel();
			BeanUtils.copyProperties(entity, model);
			return model;
		}
		return null;
	}

	@Override
	public List<TriggerModel> fetchDataWhereStatusIsPendig() {
		List<CoTriggerEntity> list = trepo.findAll().stream().filter(s -> s.getTrigStatue().equalsIgnoreCase("p"))
				.toList();
		return list.stream().map(entity -> {
			TriggerModel model = new TriggerModel();
			BeanUtils.copyProperties(entity, model);
			return model;
		}).toList();

	}

	@Override
	public EligibilityModel fetchByCaseNumber(Long caseno) {
		EligibilityEntity entity = erepo.findByCaseNumber(caseno);
		EligibilityModel model = new EligibilityModel();
		BeanUtils.copyProperties(entity, model);
		return model;
	}

	@Override
	public Boolean updateTrigger(TriggerModel trigModel) {
		Optional<CoTriggerEntity> opt = trepo.findById(trigModel.getTriggerId());
		if(opt.isPresent()) {
			CoTriggerEntity entity = opt.get();
			BeanUtils.copyProperties(trigModel, entity);
			entity.setUpdateDate(new Date());
			entity.setTrigStatue("C");
			trepo.save(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public List<TriggerModel> findByBucket(int bucketSize, int bucketNumber) {
		List<CoTriggerEntity> listEntity = trepo.findByBucket(bucketSize, bucketNumber-1);
		return listEntity.stream()
				         .map(entity->{
				        	 TriggerModel model = new TriggerModel();
				        	 BeanUtils.copyProperties(entity, model);
				        	 return model;
				         }).toList();
	}
	
	@Override
	public void saveEligibilityData(Indvinfo info) throws JsonProcessingException {	
		
		/*PlanInfo body = consume.planCheckEligibility(info).getBody();
		EligibilityEntity entity = new EligibilityEntity();
		entity.setBenifitAmount(500);
		entity.setCaseNumber(info.getCaseNumber());
		entity.setMsg(body.getMsg());
		entity.setPlanStartDate(info.getPlanStrtDate());
		entity.setPlanEndDate(info.getPlanEndDate());
		entity.setPlanName(body.getPlanName());
		entity.setPlanStatus(body.getPlanStatus());
		EligibilityEntity eligible = erepo.save(entity);
		String msg = "Successfullt inserted into EligibilityTable id is:"+eligible.getEdTraceId();
		*/	
		//convert Object to String
		String jsonString = mapper.writeValueAsString(info);
		
		// Prepare Http header for post request
		HttpHeaders header = new HttpHeaders();
      header.setContentType(MediaType.APPLICATION_JSON);
      
      //prepare post url ready
      String producerUrl = "http://localhost:8080/checkplan";
      
      // pre pare Response Entity
      HttpEntity<String> httpEntity = new HttpEntity<>(jsonString,header);
      ResponseEntity<String> resEntity = restTemplate.exchange(producerUrl, HttpMethod.POST, httpEntity, String.class);
      
      //get response body
      String jbody = resEntity.getBody();
      
      //convert String body to Object form
      PlanInfo body = mapper.readValue(jbody, PlanInfo.class);
      
      //create Eligibility Entity object
      EligibilityEntity entity = new EligibilityEntity();
        //set the data to entity
		entity.setBenifitAmount(500);
		entity.setCaseNumber(info.getCaseNumber());
		entity.setMsg(body.getMsg());
		entity.setPlanStartDate(info.getPlanStrtDate());
		entity.setPlanEndDate(info.getPlanEndDate());
		entity.setPlanName(body.getPlanName());
		entity.setPlanStatus(body.getPlanStatus());
		EligibilityEntity eligible = erepo.save(entity);
		String msg = "Successfullt inserted into EligibilityTable id is:"+eligible.getEdTraceId();

	}
}
