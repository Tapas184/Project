package fis.his.case_workers_management.controller.unlockaccount;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static fis.his.case_workers_management.constant.LogConstant.*;
import fis.his.case_workers_management.model.CwAndAdPojo;
import fis.his.case_workers_management.model.UnlockAccountPojo;
import fis.his.case_workers_management.service.adminandcw.AdminAndCwServiceInterface;
import fis.his.case_workers_management.utils.password.IPasswordUtils;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/unlock")
public class UnlokAccountController {

	@Autowired
	private AdminAndCwServiceInterface service;
	
	@Autowired
	private IPasswordUtils pass;

	@GetMapping("/unlockaccount")
	public String unlockAccount(@RequestParam("email") String email,
			@ModelAttribute("unlockpojo") UnlockAccountPojo pojo, HttpSession ses) {
		log.info(METHOD_EXECUTION_STARTED+" unlockAccount");
		CwAndAdPojo user = service.getuser(email);
		ses.setAttribute("temppass", user.getPwd());
		ses.setAttribute("email", email);
		ses.setAttribute("fname", user.getFname());
		ses.setAttribute("lname", user.getLname());
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/unlockAccount_jsp/unlockacc";
	}

	@PostMapping("/postunlock")
	public String postUnlockAccount(HttpSession ses,
			                        @ModelAttribute("unlockpojo")UnlockAccountPojo pojo,
			                        Map<String, Object> map,
			                        RedirectAttributes redirect) throws Exception {
		log.info(METHOD_EXECUTION_STARTED+" postUnlockAccount");
		String temppass = (String)ses.getAttribute("temppass");
		if(temppass.equals(pojo.getTempass())) {
			log.debug(MTHOD_DEBUG_STARTED);
			String mail	=(String)ses.getAttribute("email");
			CwAndAdPojo userPojo = new CwAndAdPojo();
			userPojo.setEmailid(mail);
			userPojo.setPwd(pass.encryption(pojo.getConfpassword()));
			String result = service.accountUpdate(userPojo);
			ses.setAttribute("resultmsg", result);
			return "case_workers_management/unlockAccount_jsp/unlocksuccessmsg";
		}
		
		String errorMsg = "Temporary password is not correct, Kindly entered correctly";
		redirect.addFlashAttribute("errorMsg", errorMsg);
		log.debug(MTHOD_DEBUG_ENDED);
		log.info(METHOD_EXECUTION_ENDED);
		return "redirect:unlockaccount";
	}
}
