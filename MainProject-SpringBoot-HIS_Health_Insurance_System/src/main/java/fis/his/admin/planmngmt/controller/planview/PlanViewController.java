package fis.his.admin.planmngmt.controller.planview;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fis.his.admin.planmngmt.model.PlanModel;
import fis.his.admin.planmngmt.service.PlanServiceInterface;

@Controller
@RequestMapping("/planview")
public class PlanViewController {

	@Autowired
	private PlanServiceInterface planservice;
	
	@GetMapping("/view")
	public String planView(Map<String, Object> map) {
		List<PlanModel> allList = planservice.getAllList();
		map.put("modellist", allList);
		return "Plan_management/viewplan/viewplan";
	}
	
	@GetMapping("/change")
	public String changeStatus(@RequestParam("id") Integer id) {
		planservice.changeStatus(id);
		return "redirect:view";
	}
	@GetMapping("/edit")
	public String edit(@RequestParam("id") Integer id,
			          HttpSession ses) {
		PlanModel model = planservice.getPlanDetails(id);
		ses.setAttribute("model", model);
		return "redirect:/plancreate/create";
	}
}
