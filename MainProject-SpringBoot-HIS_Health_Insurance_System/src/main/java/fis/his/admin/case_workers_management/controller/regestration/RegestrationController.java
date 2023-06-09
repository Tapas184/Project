package fis.his.admin.case_workers_management.controller.regestration;

import static fis.his.admin.case_workers_management.constant.LogConstant.*;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fis.his.admin.case_workers_management.model.CwAndAdPojo;
import fis.his.admin.case_workers_management.service.adminandcw.AdminAndCwServiceInterface;
import fis.his.admin.case_workers_management.service.role.RolesServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/registration")
public class RegestrationController {
	
	@Autowired
	private RolesServiceInterface rolesService;
	
	@Autowired
	private AdminAndCwServiceInterface adminandcwservice;
	
	@GetMapping("/home")
	public String showRegistrationHome() {
		log.info(METHOD_EXECUTION_STARTED+" showRegistrationHome");
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/jsp/home";
	}

	@GetMapping("/newregistration")
	public String registrationForm(@ModelAttribute("emp") CwAndAdPojo pojo,
			                       Map<String, Object> map) {
		log.info(METHOD_EXECUTION_STARTED+" registrationForm");
		List<String> roleList = rolesService.getRoleList();
		map.put("roleslist", roleList);
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/jsp/newregformforcwandadmin";
	}
	
	@GetMapping("/validmail")
	@ResponseBody
	public String validMailid(@RequestParam("email") String email){
		log.info(METHOD_EXECUTION_STARTED+" validMailid");
		String result = adminandcwservice.checkMailAvailableOrNot(email);
		log.info(METHOD_EXECUTION_ENDED);
		return result;
	}
	
	@PostMapping("/postreg")
	public String postRegistration(@ModelAttribute("emp") CwAndAdPojo pojo,
			                        HttpSession ses,
			                        Map<String, Object> map) {
		log.info(METHOD_EXECUTION_STARTED+" postRegistration");
		String result = adminandcwservice.createNewAdminOrCw(pojo);
		ses.setAttribute("resultmsg", result);
		ses.setMaxInactiveInterval(9);
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/jsp/displaymsg";
	}
}
