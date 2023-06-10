package fis.his.admin.planmngmt.controller.plancreate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fis.his.admin.planmngmt.model.PlanModel;
import fis.his.admin.planmngmt.service.PlanServiceInterface;

@Controller
@RequestMapping("/plancreate")
public class PlanCreateController {

	@Autowired
	private PlanServiceInterface planservice;
	
	@GetMapping("/create")
	public String planCreate(@ModelAttribute("pmodel") PlanModel pmodel,
			                 HttpSession ses,
			                 RedirectAttributes redirect) {
		Object attribute = ses.getAttribute("model");
		ses.removeAttribute("model");
		if(attribute!=null) {
			PlanModel m = (PlanModel) attribute;
			BeanUtils.copyProperties(m, pmodel);
		}
		
		return"Plan_management/createplan/plan$create";
	}
	
	@PostMapping("/postcreate")
	public String postPlanCrate(@ModelAttribute("pmodel") PlanModel pmodel,
			                    RedirectAttributes redirect) {
		String result = planservice.newPlan(pmodel);
		redirect.addFlashAttribute("resultmsg", result);
		return "redirect:create";
	}
}
