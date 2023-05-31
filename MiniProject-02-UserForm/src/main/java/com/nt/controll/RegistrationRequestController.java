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

import com.nt.constant.StringConstatnt;
import com.nt.entity.UserEntity;
import com.nt.model.UserPojo;
import com.nt.service.UserRegistrationInterface;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RegistrationRequestController {

	@Autowired
	private UserRegistrationInterface service;

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
		log.info(StringConstatnt.METHOD_EXECUTION_START+" mailVerification");
		String parameter = req.getParameter("email");
		return service.userDetailsFindByMail(parameter);
	}

	/**
	 * Method for after details submit type: POST
	 * 
	 * @return : String
	 * @param : Entity Object
	 */
	@PostMapping("/userReg")
	public String userRegistration(@ModelAttribute("user") UserPojo resUser, HttpSession ses) {
		log.info(StringConstatnt.METHOD_EXECUTION_START+" userRegistration");
		try {
			log.debug(StringConstatnt.DEBUG_EXECUTION_STARTED);
		// create a Entity object
		UserEntity desuser = new UserEntity();
		BeanUtils.copyProperties(resUser, desuser);
		// call the service method
		String result = service.userInsert(desuser);
		// set message to session object for displaying msg
		ses.setAttribute("msg", result);
		log.debug(StringConstatnt.DEBUG_EXECUTION_ENDED);
		}catch (Exception e) {
			log.error(StringConstatnt.ERROR_EXECUTION_STARTED);
		}
		log.info(StringConstatnt.METHOD_EXECUTION_ENDED);
		return "response";
	}

	@GetMapping("/loadpage")
	public String getCountryLoaded(Map<String, Map<Integer, String>> map, @ModelAttribute("user") UserPojo user) {

		log.info(StringConstatnt.METHOD_EXECUTION_START+" getCountryLoaded");
		Map<Integer, String> countriList = service.getCountrisInfo();
		log.info("controller-getCountryLoaded-called service");
		map.put("countryList", countriList);
		log.info(StringConstatnt.METHOD_EXECUTION_ENDED);
		return "regfrom";
	}

	@GetMapping("/getstate")
	@ResponseBody
	public Map<Integer, String> getStateInfo(@RequestParam("cid") Integer countryId)
	{
		log.info(StringConstatnt.ERROR_EXECUTION_STARTED+" getStateInfo");
		return service.getStateInfo(countryId);
	}

	@GetMapping("/getcity")
	@ResponseBody
	public Map<Integer, String> getCityInfo(@RequestParam("sid") Integer stateId) {
		log.info("executing getCityInfo()");
		log.info("called controller-getCityInfo-service");
		return service.getCityInfo(stateId);
	}

}// end of controller class
