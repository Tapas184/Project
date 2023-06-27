package fis.his.dc.snap.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.dc.snap.entity.SnapEntity;
import fis.his.dc.snap.model.SnapModel;
import fis.his.dc.snap.repository.SnapRepo;
import fis.his.dc.snap.service.InterfaceSnap;

@Service
public class SnapImpl implements InterfaceSnap {
	
	@Autowired
	private SnapRepo snapRepo;

	@Override
	public String saveSnap(SnapModel snap) {
		SnapEntity entity = new SnapEntity();
		BeanUtils.copyProperties(snap, entity);
		return "Snap data saved id"+snapRepo.save(entity).getSnapId();
		
	}

	@Override
	public SnapModel getSnapData(Long id) {
		SnapModel model=null;
		Optional<SnapEntity> opt = snapRepo.findById(id);
		if(opt.isPresent()) {
			SnapEntity snapEntity = opt.get();
			 model = new SnapModel();
			BeanUtils.copyProperties(snapEntity, model);
		}
		return model;
	}
}
