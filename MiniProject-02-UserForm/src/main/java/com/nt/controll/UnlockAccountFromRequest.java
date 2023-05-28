package com.nt.controll;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nt.model.UnlockAccount;

@Controller
public class UnlockAccountFromRequest {

	@GetMapping("/unlockaccount")
	public String unlockAcc(@ModelAttribute("acc") UnlockAccount acc,
			                @RequestParam("email") String email,
			                HttpSession ses,
			                Map<String, Object> map) {
		//set email into map model
		ses.setAttribute("useremail", email);
	 map.put("email", email);
	 
	 //return LVN
		return "unlockAccount";
	}
	@PostMapping("/unlockAcc")
	public String postUnlockAcc(@ModelAttribute("acc") UnlockAccount acc,
			                    HttpSession ses,
			                 Map<String, Object> map) {
		String email = (String)ses.getAttribute("useremail");
		System.out.println(email);
		acc.setEmail(email);
		System.out.println(acc);
		map.put("email", acc.getEmail());
		return "success";
	}
}//End of class
