package fis.his.application_registration.service;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.application_registration.entity.AREntity;
import fis.his.application_registration.model.ARModel;
import fis.his.application_registration.repository.ARRepository;

@Service
public class ARServiceImpl implements ARServiceInterface {

	@Autowired
	private ARRepository arrepo;
	
	@Override
	public String createApplication(ARModel model) {
		if(model.getId()==null) {
			AREntity entity = new AREntity();
			BeanUtils.copyProperties(model, entity);
			String[] split = model.getDob().split("-");
			entity.setDob(LocalDate.of(Integer.parseInt(split[2]), 
					                    Integer.parseInt(split[1]),
					                    Integer.parseInt(split[0])));
			AREntity user = arrepo.save(entity);
			return "Application registration successfully with id: "+user.getId();
		}
		return null;
	}
}
