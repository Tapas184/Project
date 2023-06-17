package fis.his.dc.service.dcplanservice;

import org.codehaus.stax2.ri.typed.ValueDecoderFactory.DecimalDecoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.admin.planmngmt.entity.PlanEntity;
import fis.his.admin.planmngmt.service.PlanServiceInterface;
import fis.his.application_registration.entity.AREntity;
import fis.his.application_registration.model.ARModel;
import fis.his.application_registration.service.ARServiceInterface;
import fis.his.dc.entity.DcPlanEntity;
import fis.his.dc.model.DcPlanModel;
import fis.his.dc.repository.DcPlanRepository;

@Service
public class DcPlanServiceImpl implements DcplanServiceInterface {
	
	@Autowired
	private DcPlanRepository dcplanrepo;
	@Autowired
	private PlanServiceInterface service;
	@Override
	
	public String createPlan(DcPlanModel model) {
		if(model.getPlanid()==null) {
			DcPlanEntity dcentity = new DcPlanEntity();
			BeanUtils.copyProperties(model, dcentity);
			dcplanrepo.save(dcentity);
			return "Successfully Plan created";
		}
		return null;
	}

}
