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

import fis.his.admin.planmngmt.service.PlanServiceInterface;
import fis.his.application_registration.model.ARModel;
import fis.his.application_registration.service.ARServiceInterface;
import fis.his.dc.model.DcPlanModel;
import fis.his.dc.service.dcplanservice.DcplanServiceInterface;

@Controller
@RequestMapping("/Dcplan")
public class PlanController {

	@Autowired
	private DcplanServiceInterface service;
	
	@Autowired
	private ARServiceInterface arservice;
	@Autowired
	private PlanServiceInterface planservice;
	
	@GetMapping("/dcplanhome")
	public String showPlanCreateForm(@ModelAttribute("model") DcPlanModel model,
			                         @RequestParam("id") Integer id,
			                         Map<String, Object> map) {
		ARModel armodel = arservice.fetchApplication(id);
		List<String> list = planservice.getAllList()
		           .stream()
		           .map(s->s.getPlanName())
		           .toList();
		BeanUtils.copyProperties(armodel, model);
		map.put("rolelist", list);
	 return "DCModule/Dcplan/chooseplan";	
	}
	
	@PostMapping("/postdcplanhome")
	public String createPlan(@ModelAttribute("model") DcPlanModel model,
			                 RedirectAttributes redirect) {
		String result = service.createPlan(model);
		redirect.addFlashAttribute("result",result);
		return "redirect:childdcform";
	}
	@GetMapping("/childdcform")
	public String showChildDcForm() {
		return "DCModule/childdc/childdc";
	}
}
