package com.nt.controll;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
		log.info("*** Home Page ****");
		return "home";
	}// end of the method showHomePage

	@PostMapping("/login")
	public String afterLogin(@ModelAttribute("acc") AccountLogin acc, Map<String, Object> map) {
		log.info("****cls-HomePageController, Method-afterLogin started****");
		try {
			log.debug("***** Method- afterLogin debug started *******");
			boolean check = false;
			UserEntity user = service.findUserByMail(acc.getEmail()); // call the service calss
			if (user != null && acc.getPassword().equals(user.getPwd())) {
				String successMsg = "SuccessFully loggedIn" + user.getFname();// string msg
				map.put("successMsg", successMsg); // kept success message in model view
				check = true; // Assign check variable true
				map.put("check", check); // kept check in model view
			} else {
				// else condition failed
				String failuerMsg = "Failed in login kindly input correct ID and password"; // failed message
				map.put("failuerMsg", failuerMsg); // kept failure message in model view
				map.put("check", check); // kept failure message in model view
			}
		} catch (Exception e) {
			log.debug("***** Method- afterLogin debug ended *******");
		}
		return "success";
	}// afterLogin

	@GetMapping("/forgotpwd")
	public String forgotPwdFrm(@ModelAttribute("frgt") ForgotPwd frgt) {
		return "forgot/forgetPwd";
	}// end of forgotPwdFrm

	@PostMapping("/frgot")
	public String checkMailIsValidOrNot(@ModelAttribute("frgt") ForgotPwd frgt, Map<String, Object> map,
			HttpSession ses) {
		log.info("********checkMailIsValidOrNot Method started**********");
		try {
			log.debug("********Debug started***********");
			UserEntity user = service.findUserByMail(frgt.getEmail()); // get user by mail
			ses.setAttribute("userMail", frgt.getEmail()); // kept mail into the HTTPsession attributes
			if (user != null) {
				PhoneNumber to = new PhoneNumber("+91" + user.getPhno().toString()); // Create a phone object and
																						// passing user phone number
				String otp = TempPassWrd.otp(); // generate oTp
				ses.setAttribute("otp", otp); // kept otp in HTTP session
				String body = "Your OTP(One Time Password) is " + otp + "Don't share any one Use for reset password"; // Prepare
																														// sms
																														// body
				smsservice.sendSms(to, body); // call the smsservice method
				return "/forgot/resetpwd"; // return LVN
			}
			String notFoundUser = "Given Email not found"; // else part failure message prepared
			map.put("notfound", notFoundUser); // kept failure message in model views
			log.debug("********Debug ended***********");
		} catch (Exception e) {
			log.error("Error : " + e.getMessage());
		}
		log.info("********checkMailIsValidOrNot Method ended**********");
		return "/forgot/forgotError"; // return LVN
	}// checkMailIsValidOrNot method closed

	@PostMapping("/resetPwd")
	public String changePassword(HttpSession ses, @ModelAttribute("frgt") ForgotPwd frgt, Map<String, Object> map) {
		log.info("*******changePassword started*******");
		try {
			log.debug("<changePassword started Debug>");
			String otp = (String) ses.getAttribute("otp");

			if (otp.equals(frgt.getOtp())) {
				UserEntity user = service.findUserByMail((String) ses.getAttribute("userMail"));
				user.setPwd(frgt.getNewPass());
				service.userInsert(user);
				ses.removeAttribute("otp");
				return "/forgot/successpwdreset";
			}
			log.debug("<changePassword end Debug>");
		} catch (Exception e) {
			log.error("Error :" + e.getMessage());
		}
		log.info("*******changePassword ended*******");
		return "/forgot/ErrorInResetPwd";
	}

}// end class for HomePageController
