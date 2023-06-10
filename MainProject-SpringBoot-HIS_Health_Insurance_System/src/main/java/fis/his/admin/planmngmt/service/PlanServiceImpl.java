package fis.his.admin.planmngmt.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.admin.planmngmt.entity.PlanEntity;
import fis.his.admin.planmngmt.model.PlanModel;
import fis.his.admin.planmngmt.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanServiceInterface {

	@Autowired
	private PlanRepository planrepo;

	@Override
	public String newPlan(PlanModel plan) {
		String startDate = plan.getStartDate();
		String endDate = plan.getEndDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (plan.getPlanId() == null) {
			PlanEntity entity = new PlanEntity();
			entity.setPlanName(plan.getPlanName().toUpperCase());
			entity.setPlanDescription(plan.getPlanDescription());
			entity.setStartDate(LocalDate.parse(startDate, formatter));
			entity.setEndDate(LocalDate.parse(endDate, formatter));
			entity.setPlanStatus('Y');
			planrepo.save(entity);
			return "New Plan Created with Name" + entity.getPlanName() + " and id :" + entity.getPlanId();
		} else {
			Optional<PlanEntity> opt = planrepo.findById(plan.getPlanId());
			if (opt.isPresent()) {
				PlanEntity entity = opt.get();
				entity.setPlanDescription(plan.getPlanDescription());
				entity.setStartDate(LocalDate.parse(startDate, formatter));
				entity.setEndDate(LocalDate.parse(endDate, formatter));
				planrepo.save(entity);
			}
			return opt.get().getPlanName()+"Plan successfully updated";
		}
	}

	@Override
	public List<PlanModel> getAllList() {
		List<PlanModel> modelList = new ArrayList<>();
		planrepo.findAll().stream().forEach(s -> {
			PlanModel model = new PlanModel();
			BeanUtils.copyProperties(s, model);
			model.setStartDate(s.getStartDate().toString());
			model.setEndDate(s.getEndDate().toString());
			modelList.add(model);
		});
		return modelList;
	}

	@Override
	public boolean changeStatus(Integer id) {
		Optional<PlanEntity> opt = planrepo.findById(id);
		if (opt.isPresent()) {
			PlanEntity planEntity = opt.get();
			if (planEntity.getPlanStatus() != 'Y') {
				planEntity.setPlanStatus('Y');
			} else {
				planEntity.setPlanStatus('N');
			}
			planrepo.save(planEntity);
		}
		return true;
	}

	@Override
	public PlanModel getPlanDetails(Integer id) {
		Optional<PlanEntity> opt = planrepo.findById(id);
		PlanModel model = new PlanModel();
		if (opt.isPresent()) {
			PlanEntity planEntity = opt.get();
			BeanUtils.copyProperties(planEntity, model);
			model.setStartDate(String.valueOf(planEntity.getStartDate()));
			model.setEndDate(String.valueOf(planEntity.getEndDate()));
		}
		return model;
	}
}
