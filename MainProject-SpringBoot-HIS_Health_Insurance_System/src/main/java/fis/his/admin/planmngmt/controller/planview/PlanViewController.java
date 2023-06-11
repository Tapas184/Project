package fis.his.admin.planmngmt.controller.planview;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fis.his.admin.planmngmt.entity.PlanEntity;
import fis.his.admin.planmngmt.model.PlanModel;
import fis.his.admin.planmngmt.service.PlanServiceInterface;

@Controller
@RequestMapping("/planview")
public class PlanViewController {

	@Autowired
	private PlanServiceInterface planservice;
	
	@GetMapping("/view")
	public String planView(Map<String, Object> map,
			               @PageableDefault(direction = Direction.ASC,page = 0,size = 3,sort = "planName") Pageable pageable) {
		//List<PlanModel> allList = planservice.getAllList();
		Page<PlanEntity> page = planservice.findAllPlan(pageable);
		map.put("page", page);
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
