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
import com.nt.service.UserRegistrationInterface;

@Controller
public class HomePageController {

	@Autowired
	private UserRegistrationInterface service;

	@GetMapping("/")
	public String showHomePage(@ModelAttribute("acc") AccountLogin acc) {
		return "home";
	}// end of the method showHomePage

	@PostMapping("/login")
	public String afterLogin(@ModelAttribute("acc") AccountLogin acc, Map<String, Object> map) {
		boolean check = false;
		UserEntity user = service.findUserByMail(acc.getEmail());
		if (user != null && acc.getPassword().equals(user.getPwd())) {
			String successMsg = "SuccessFully loggedIn" + user.getFname();
			map.put("successMsg", successMsg);
			check = true;
			map.put("check", check);
		} else {
			String failuerMsg = "Failed in login kindly input correct ID and password";
			map.put("failuerMsg", failuerMsg);
			map.put("check", check);
		}
		return "success";
	}// afterLogin

	@GetMapping("/forgotpwd")
	public String forgotPwdFrm(@ModelAttribute("frgt") ForgotPwd frgt) {
		return "forgot/forgetPwd";
	}//end of forgotPwdFrm
	
	@PostMapping("/frgot")
	public String checkMailIsValidOrNot(@ModelAttribute("frgt") ForgotPwd frgt,
			                    Map<String, Object> map,
			                    HttpSession ses) {
		UserEntity user = service.findUserByMail(frgt.getEmail());
		if(user!=null) {
			return "/forgot/resetpwd";
		}
		String notFoundUser = "Given Email not found";
		map.put("notfound", notFoundUser);
		return "/forgot/forgotError";
	}

}// end class for HomePageController
