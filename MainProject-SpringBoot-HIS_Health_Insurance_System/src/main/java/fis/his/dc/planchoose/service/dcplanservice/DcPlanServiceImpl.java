package fis.his.dc.planchoose.service.dcplanservice;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.dc.planchoose.entity.DcPlanEntity;
import fis.his.dc.planchoose.model.DcPlanModel;
import fis.his.dc.planchoose.repository.DcPlanRepository;

@Service
public class DcPlanServiceImpl implements DcplanServiceInterface {
	
	@Autowired
	private DcPlanRepository dcplanrepo;
	
	@Override
	public Long createPlan(DcPlanModel model) {
		if(model.getPlanid()==null) {
			DcPlanEntity dcentity = new DcPlanEntity();
			BeanUtils.copyProperties(model, dcentity);
			return dcplanrepo.save(dcentity).getPlanid();
		}
		return null;
	}

}
