package fis.his.co.ed.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.co.ed.entity.CoTriggerEntity;
import fis.his.co.ed.entity.EligibilityEntity;
import fis.his.co.ed.model.EligibilityModel;
import fis.his.co.ed.model.TriggerModel;
import fis.his.co.ed.repository.EligibilityRepo;
import fis.his.co.ed.repository.TriggerRepository;

@Service
public class EligibilityServiceImpl implements EligibilityInserface {

	@Autowired
	private EligibilityRepo erepo;

	@Autowired
	private TriggerRepository trepo;

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
}
