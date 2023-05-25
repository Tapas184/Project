package com.nt.controll;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nt.entity.UserEntity;
import com.nt.model.UserPojo;
import com.nt.service.UserRegistrationInterface;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RequestController {

	@Autowired
	private UserRegistrationInterface service;

	@GetMapping("/")
	public String showHome() {
		log.info("Class Name" + this.getClass().getName() + " Method : showHome()");
		return "home";
	}

	@GetMapping("/reg")
	public String userRegistrationForm(@ModelAttribute("user") UserPojo user) {
		return "redirect:loadpage";

	}

	/**
	 * Mail verification from client side type : GET Url : /validmail
	 * 
	 * @return String
	 * @param String
	 */
	@GetMapping("/validmail")
	@ResponseBody
	public String mailVerification(HttpServletRequest req) {
		String parameter = req.getParameter("email");
		System.out.println(parameter);
		String result = service.userDetailsFindByMail(parameter);
		System.out.println(result);
		return result;
	}

	/**
	 * Method for after details submit type: POST
	 * 
	 * @return : String
	 * @param : Entity Object
	 */
	@PostMapping("/userReg")
	public String userRegistration(@ModelAttribute("user") UserPojo resUser, HttpSession ses) {
		// create a Entity object
		UserEntity desuser = new UserEntity();
		BeanUtils.copyProperties(resUser, desuser);
		// call the service method
		String result = service.userInsert(desuser);
		// set message to session object for displaying msg
		ses.setAttribute("msg", result);
		return "response";
	}

	@GetMapping("/loadpage")
	public String getCountryLoaded(Map<String, Map<Integer, String>> map, @ModelAttribute("user") UserPojo user) {

		log.info("Entered into controller-getCountryLoaded()");
		Map<Integer, String> countriList = service.getCountrisInfo();
		log.info("controller-getCountryLoaded-called service");
		map.put("countryList", countriList);
		return "regfrom";
	}

	@GetMapping("/getstate")
	@ResponseBody
	public Map<Integer, String> getStateInfo(@RequestParam("cid") Integer countryId) {
		log.info("Executing getStateInfo");
		log.info("Call the controller - getStateInfo()-service");
		Map<Integer, String> map = service.getStateInfo(countryId);
		return map;
	}

	@GetMapping("/getcity")
	@ResponseBody
	public Map<Integer, String> getCityInfo(@RequestParam("sid") Integer stateId) {
		log.info("executing getCityInfo()");
		log.info("called controller-getCityInfo-service");
		System.out.println(service.getCityInfo(stateId));
		return service.getCityInfo(stateId);
	}

}// end of controller class
