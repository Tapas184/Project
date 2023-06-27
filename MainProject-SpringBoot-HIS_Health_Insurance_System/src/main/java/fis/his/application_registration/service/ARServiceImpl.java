package fis.his.application_registration.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional
	public String createApplication(ARModel model) {
		if (model.getId() == null) {
			AREntity user = null;
			AREntity entity = new AREntity();
			BeanUtils.copyProperties(model, entity);
			String[] split = model.getDob().split("-");
			entity.setDob(
					LocalDate.of(Integer.parseInt(split[2]), 
							     Integer.parseInt(split[1]), 
							     Integer.parseInt(split[0])));
			entity.setVrifyStatus("N");
			try {
				user = arrepo.save(entity);
				entityManager.flush();
			} catch (Exception e) {
				entityManager.clear();
				throw e;
			}
			return "Application registration successfully with id: " + user.getId();
		} else {
			Optional<AREntity> opt = arrepo.findById(model.getId());
			if (opt.isPresent()) {
				AREntity entity = opt.get();
				BeanUtils.copyProperties(model, entity);
				try {
					arrepo.save(entity);
					entityManager.flush();
				} catch (Exception e) {
					entityManager.clear();
					throw e;
				}
			}
		}
		return "Application Number" + model.getId() + " updated";
	}

	@Override
	public List<ARModel> allApplication() {
		List<AREntity> listEntity = arrepo.findAll();
		return listEntity.stream().map(entity -> {
			ARModel model = new ARModel();
			BeanUtils.copyProperties(entity, model);
			model.setDob(entity.getDob().toString());
			return model;
		}).toList();

	}

	@Override
	public ARModel fetchApplication(Integer id) {
		Optional<AREntity> opt = arrepo.findById(id);
		ARModel model = new ARModel();
		if (opt.isPresent()) {
			AREntity entity = opt.get();
			BeanUtils.copyProperties(entity, model);
			model.setDob(entity.getDob().toString());
		}
		return model;
	}
}
