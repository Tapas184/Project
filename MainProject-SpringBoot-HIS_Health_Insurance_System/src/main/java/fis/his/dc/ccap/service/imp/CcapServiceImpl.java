package fis.his.dc.ccap.service.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.dc.ccap.entity.CcapEntity;
import fis.his.dc.ccap.model.CcapModel;
import fis.his.dc.ccap.repository.CcapRepository;
import fis.his.dc.ccap.service.CcapInterface;

@Service
public class CcapServiceImpl implements CcapInterface {

	
	@Autowired
	private CcapRepository crepo;
	
	/**
	 * This method is used for save the child care plan data to DB
	 */
	@Override
	public boolean saveCcapPlan(CcapModel model) {
		try {
			
			if(model.getId()==null) {
				CcapEntity entity = new CcapEntity();
				BeanUtils.copyProperties(model, entity);
				crepo.save(entity);
				return true;
			}
			
		}catch (Exception e) {
			throw new IllegalArgumentException("Problem in saving Ccap object");
		}
		return false;
	}

}
