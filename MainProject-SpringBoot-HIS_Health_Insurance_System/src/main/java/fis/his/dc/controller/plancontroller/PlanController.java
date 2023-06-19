package fis.his.dc.controller.plancontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static fis.his.admin.case_workers_management.constant.LogConstant.*;
import fis.his.admin.planmngmt.service.PlanServiceInterface;
import fis.his.application_registration.model.ARModel;
import fis.his.application_registration.service.ARServiceInterface;
import fis.his.dc.model.DcPlanModel;
import fis.his.dc.service.dcplanservice.DcplanServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/Dcplan")
@Slf4j
public class PlanController {

	@Autowired
	private DcplanServiceInterface service;
	
	@Autowired
	private ARServiceInterface arservice;
	@Autowired
	private PlanServiceInterface planservice;
	
	
	/**
	 * {@summary : Method is used for Show plan creation form}
	 * @param model : DcPlanModel
	 * @param id :Integer
	 * @param map : ModelAndView
	 * @return : String LVN
	 */
	@GetMapping("/dcplanhome")
	public String showPlanCreateForm(@ModelAttribute("model") DcPlanModel model,
			                         @RequestParam("id") Integer id,
			                         Map<String, Object> map) {
		log.info(METHOD_EXECUTION_STARTED+"-showPlanCreateForm");
		ARModel armodel = arservice.fetchApplication(id);
		List<String> list = planservice.getAllList()
		           .stream()
		           .map(s->s.getPlanName())
		           .toList();
		BeanUtils.copyProperties(armodel, model);
		map.put("rolelist", list);
		log.info(METHOD_EXECUTION_ENDED);
	 return "DCModule/Dcplan/chooseplan";	
	}
	
	
	/**
	 * {@summary : Method is used for after post plan form}
	 * @param model :DcPlanModel 
	 * @param redirect :/Dcplan/childdcform
	 * @return : String LVN
	 */
	@PostMapping("/postdcplanhome")
	public String createPlan(@ModelAttribute("model") DcPlanModel model,
			                 RedirectAttributes redirect) {
		log.info(METHOD_EXECUTION_STARTED+"-createPlan");
		String result = service.createPlan(model);
		redirect.addFlashAttribute("result",result);
		log.info(METHOD_EXECUTION_ENDED);
		return "redirect:childdcform";
	}
	
	/**
	 * {@summary : Method is used for show child Data collection form}
	 * @return : String logical view name
	 */
	@GetMapping("/childdcform")
	public String showChildDcForm() {
		return "DCModule/childdc/childdc";
	}
}
