package com.nt.controll;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.constant.StringConstatnt;
import com.nt.entity.UserEntity;
import com.nt.model.AccountLogin;
import com.nt.model.ForgotPwd;
import com.nt.random.TempPassWrd;
import com.nt.service.UserRegistrationInterface;
import com.nt.service.smsservice.SmsInterface;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomePageController {

	@Autowired
	private UserRegistrationInterface service;
	@Autowired
	private SmsInterface smsservice;

	@GetMapping("/")
	public String showHomePage(@ModelAttribute("acc") AccountLogin acc) {
		log.info(StringConstatnt.METHOD_EXECUTION_START+"showHomePage");
		return "home";
	}// end of the method showHomePage

	@PostMapping("/login")
	public String afterLogin(@ModelAttribute("acc") AccountLogin acc, 
			                Map<String, Object> map) {
		log.info(StringConstatnt.METHOD_EXECUTION_START);
		int s;
		try {
			log.debug(StringConstatnt.DEBUG_EXECUTION_STARTED);
			UserEntity user = service.findUserByMail(acc.getEmail()); // call the service calss
			if (user != null && acc.getPassword().equals(user.getPwd())) {
				String successMsg = "SuccessFully loggedIn" + user.getFname();// string msg
				s=1;
				map.put("s", s);
				map.put("successMsg", successMsg);
				 // kept success message in model view
			} else {
				s=2;
				map.put("s", s);
				// else condition failed
				String failuerMsg = StringConstatnt.WRONG_ID_PASSWORD; // failed message
				map.put("failuerMsg", failuerMsg);
			}
		} catch (Exception e) {
			log.error(StringConstatnt.ERROR_EXECUTION_STARTED+e.getMessage());
		}
		return "success";
	}// afterLogin

	@GetMapping("/forgotpwd")
	public String forgotPwdFrm(@ModelAttribute("frgt") ForgotPwd frgt) {
		return "forgot/forgetPwd";
	}// end of forgotPwdFrm
	
	
	  @GetMapping("/resetpass") 
	  public String resetPwdForm(HttpSession ses, 
			                @ModelAttribute("frgt") ForgotPwd frgt, 
			                Map<String, Object> map) {
	  return "/forgot/resetpwd"; }
	 
	@PostMapping("/frgot")
	public String checkMailIsValidOrNot(@ModelAttribute("frgt") ForgotPwd frgt, Map<String, Object> map,
			HttpSession ses) {
		log.info(StringConstatnt.METHOD_EXECUTION_START+" Valid mail check");
		try {
			log.debug(StringConstatnt.DEBUG_EXECUTION_STARTED);
			UserEntity user = service.findUserByMail(frgt.getEmail()); // get user by mail
			ses.setAttribute("userMail", frgt.getEmail()); // kept mail into the HTTPsession attributes
			if (user != null) {
				PhoneNumber to = new PhoneNumber("+91" + user.getPhno().toString()); // Create a phone object and																	// passing user phone number
				String otp = TempPassWrd.otp(); // generate oTp
				ses.setAttribute("otp", otp); // kept otp in HTTP session
				String body = "Your OTP(One Time Password) is " + otp + "Don't share any one Use for reset password"; // Prepare message Body																									// body
				smsservice.sendSms(to, body); // call the smsservice method
				return "redirect:resetpass"; // return LVN
			}
			String notFoundUser = StringConstatnt.EMAIL_NOT_FOUND; // else part failure message prepared
			map.put("notfound", notFoundUser); // kept failure message in model views
			log.debug(StringConstatnt.DEBUG_EXECUTION_ENDED);
		} catch (Exception e) {
			log.error(StringConstatnt.ERROR_EXECUTION_STARTED + e.getMessage());
		}
		log.info(StringConstatnt.METHOD_EXECUTION_ENDED);
		return "/forgot/forgotError"; // return LVN
	}// checkMailIsValidOrNot method closed
	

	@PostMapping("/resetPwd")
	public String changePassword(HttpSession ses, @ModelAttribute("frgt") ForgotPwd frgt, Map<String, Object> map) {
		log.info(StringConstatnt.METHOD_EXECUTION_START+" changePassword");
		try {
			log.debug(StringConstatnt.DEBUG_EXECUTION_STARTED);
			String otp = (String) ses.getAttribute("otp");

			if (otp.equals(frgt.getOtp())) {
				UserEntity user = service.findUserByMail((String) ses.getAttribute("userMail"));
				user.setPwd(frgt.getNewPass());
				service.userInsert(user);
				ses.removeAttribute("otp");
				return "/forgot/successpwdreset";
			}
			log.debug(StringConstatnt.DEBUG_EXECUTION_ENDED);
		} catch (Exception e) {
			log.error(StringConstatnt.ERROR_EXECUTION_STARTED + e.getMessage());
		}
		log.info(StringConstatnt.METHOD_EXECUTION_ENDED);
		return "/forgot/ErrorInResetPwd";
	}

}// end class for HomePageController
