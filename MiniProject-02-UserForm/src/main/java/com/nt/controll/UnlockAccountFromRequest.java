package com.nt.controll;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.entity.UserEntity;
import com.nt.model.UnlockAccount;
import com.nt.service.UserRegistrationInterface;

@Controller
public class UnlockAccountFromRequest {

	@Autowired
	private UserRegistrationInterface service;

	@GetMapping("/unlockaccount")
	public String unlockAcc(@ModelAttribute("acc") UnlockAccount acc, @RequestParam("email") String email,
			HttpSession ses, Map<String, Object> map) {
		// set email into map model
		ses.setAttribute("useremail", email);
		map.put("email", email);

		// return LVN
		return "unlockAccount";
	}

	@PostMapping("/unlockAcc")
	public String postUnlockAcc(@ModelAttribute("acc") UnlockAccount acc, HttpSession ses, Map<String, Object> map) {
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
			//change user status
			user.setStatus("UNLOCKED");
			// call the user insert method
			String result = service.userInsert(user);
			// kept the result in Model
			map.put("successMsg", result);
			// return LVN
			return "success";
		} else {
			// false: showing wrong message
			String wrong = "Temporary password is wrong";
			map.put("wrongpass", wrong);
			map.put("email", email);
			// return LVN
			return "error";
		}
	}
}// End of cla000ss
