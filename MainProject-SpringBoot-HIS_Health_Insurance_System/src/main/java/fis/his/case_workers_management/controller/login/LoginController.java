package fis.his.case_workers_management.controller.login;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fis.his.case_workers_management.model.CwAndAdPojo;
import fis.his.case_workers_management.service.adminandcw.AdminAndCwServiceInterface;
import fis.his.case_workers_management.utils.password.IPasswordUtils;

@Controller
@RequestMapping("/userlogin")
public class LoginController {

	@Autowired
	private AdminAndCwServiceInterface service;
	
	@Autowired
	private IPasswordUtils pass;
	
	@GetMapping("/loginhome")
	public String loginHome(@ModelAttribute("logindata")CwAndAdPojo pojo) {
		return"case_workers_management/login/homepage";
	}
	
	@PostMapping("/postlogin")
	public String postLogin(@ModelAttribute("logindata") CwAndAdPojo pojo,
			                HttpSession ses,
			                RedirectAttributes redirect,
			                Map<String, Object> map) throws Exception {
		CwAndAdPojo user = service.getuser(pojo.getEmailid());
		String userPass = pass.decryption(user.getPwd());
		if(pojo.getPwd().equals(userPass)) {
			String loginSuccMsg = "Login Successfull.";
			redirect.addFlashAttribute("loginSuccMsg", loginSuccMsg);
			String userName= user.getFname()+" "+user.getLname();
			ses.setAttribute("userName", userName);
			return "redirect:/registration/home";
		}
		String errorMsg = "Incorect password";
		ses.setAttribute("errorMsg", errorMsg);
		return "case_workers_management/login/error";
	}
}
