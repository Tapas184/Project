package fis.his.dc.planchoose.controller.plancontroller;

import static fis.his.admin.case_workers_management.constant.LogConstant.METHOD_EXECUTION_ENDED;
import static fis.his.admin.case_workers_management.constant.LogConstant.METHOD_EXECUTION_STARTED;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import fis.his.dc.planchoose.PlanModel;
import fis.his.dc.planchoose.model.DcPlanModel;
import fis.his.dc.planchoose.service.dcplanservice.DcplanServiceInterface;
import fis.his.dc.snap.model.SnapModel;
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
			                         Map<String, Object> map,
			                         HttpSession ses) {
		log.info(METHOD_EXECUTION_STARTED+"-showPlanCreateForm");
		ARModel armodel = arservice.fetchApplication(id);
		List<String> list = planservice.getAllList()
		           .stream()
		           .map(s->s.getPlanName())
		           .toList();
		BeanUtils.copyProperties(armodel, model);
		map.put("rolelist", list);
		ses.setAttribute("applId", id);
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
			                 RedirectAttributes redirect,
			                HttpSession ses) {
		log.info(METHOD_EXECUTION_STARTED+"-createPlan");
		Integer attribute = (Integer)ses.getAttribute("applId");
		
		model.setId(attribute);
		 Long id = service.createPlan(model);
		String plan = model.getPlan();
		ses.setAttribute("pname", plan);
		ses.setAttribute("planid", id);
		if(id!=null)
		{
			String result = "Successfully plan registered";
			redirect.addFlashAttribute("result",result);
		}
		
		log.info(METHOD_EXECUTION_ENDED);
		return "redirect:form";
	}
	
	/**
	 * {@summary : Method is used for show child Data collection form}
	 * @return : String logical view name
	 */
	@GetMapping("/form")
	public String showChildDcForm(HttpSession ses, @ModelAttribute("model") PlanModel model) {
		String plname=(String)ses.getAttribute("pname");
		Integer attribute = (Integer)ses.getAttribute("applId");
		Long id=(Long)ses.getAttribute("planid");
		model.setApplicationId(attribute);
		model.setCaseId(Integer.parseInt(id.toString()));
		model.setPlanName(plname);
		return "DCModule/"+plname+"/"+plname;
	}
	
}
