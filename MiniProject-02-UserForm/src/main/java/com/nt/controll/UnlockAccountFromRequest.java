package com.nt.controll;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.constant.StringConstatnt;
import com.nt.entity.UserEntity;
import com.nt.model.UnlockAccount;
import com.nt.service.UserRegistrationInterface;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UnlockAccountFromRequest {

	@Autowired
	private UserRegistrationInterface service;

	@GetMapping("/unlockaccount")
	public String unlockAcc(@ModelAttribute("acc") UnlockAccount acc, @RequestParam("email") String email,
			HttpSession ses, Map<String, Object> map) {
		log.info(StringConstatnt.ERROR_EXECUTION_STARTED + " unlockAcc");
		// set email into map model
		ses.setAttribute("useremail", email);
		map.put("email", email);
		log.info(StringConstatnt.METHOD_EXECUTION_ENDED);
		// return LVN
		return "unlockAccount";
	}

	@PostMapping("/unlockAcc")
	public String postUnlockAcc(@ModelAttribute("acc") UnlockAccount acc, HttpSession ses, Map<String, Object> map) {
		log.info(StringConstatnt.ERROR_EXECUTION_STARTED + " postUnlockAcc");
		int s;
		try {
			log.debug(StringConstatnt.DEBUG_EXECUTION_STARTED);
			// get Mail id from session Attributes
			String email = (String) ses.getAttribute("useremail");
			// search user by mail id
			UserEntity user = service.findUserByMail(email);
			// get the user password
			String pwd = user.getPwd();
			// check whether actual password and user put temporary password is same or not
			if (pwd.equals(acc.getTemPwd())) {
				// true : set user password with new password
				user.setPwd(acc.getNewPwd());
				// change user status
				user.setStatus("UNLOCKED");
				s=3;
				map.put("s", s);
				// call the user insert method
				String result = service.userInsert(user);
				// kept the result in Model
				map.put("unlockMsg", result);
				// return LVN
				return "success";
			} else {
				s=4;
				map.put("s", s);
				// false: showing wrong message
				String wrong = "Temporary password is wrong";
				map.put("wrongpass", wrong);
				map.put("email", email);
				log.debug(StringConstatnt.DEBUG_EXECUTION_ENDED);
			}
		} catch (Exception e) {
			log.error(StringConstatnt.ERROR_EXECUTION_STARTED+e.getMessage());
		}
		// return LVN
		return "error";
	}

}// End of cla000ss
