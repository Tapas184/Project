package fis.his.login;

import static fis.his.admin.case_workers_management.constant.LogConstant.*;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.twilio.type.PhoneNumber;

import fis.his.admin.case_workers_management.customexception.ExceptionInFounduser;
import fis.his.admin.case_workers_management.model.CwAndAdPojo;
import fis.his.admin.case_workers_management.model.UnlockAccountPojo;
import fis.his.admin.case_workers_management.service.adminandcw.AdminAndCwServiceInterface;
import fis.his.admin.case_workers_management.utils.mail.MailInterface;
import fis.his.admin.case_workers_management.utils.password.IPasswordUtils;
import fis.his.admin.case_workers_management.utils.sms.SMSServiceInterface;
import fis.his.admin.case_workers_management.utils.temppass.RandomPassGenerator;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/userlogin")
@Slf4j
public class LoginController {

	@Autowired
	private AdminAndCwServiceInterface service;
	
	@Autowired
	private IPasswordUtils pass;
	
	@Autowired
	private SMSServiceInterface sms;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private MailInterface mail;
	@GetMapping("/loginhome")
	public String loginHome(@ModelAttribute("logindata")CwAndAdPojo pojo) {
		log.info(METHOD_EXECUTION_STARTED+" loginHome");
		return"case_workers_management/login/homepage";
	}
	
	@PostMapping("/postlogin")
	public String postLogin(@ModelAttribute("logindata") CwAndAdPojo pojo,
			                HttpSession ses,
			                RedirectAttributes redirect,
			                Map<String, Object> map) throws Exception {
		log.info(METHOD_EXECUTION_STARTED+" postLogin");
		CwAndAdPojo user = service.getuser(pojo.getEmailid());
		String userPass = pass.decryption(user.getPwd());
		if(pojo.getPwd().equals(userPass)) {
			if(user.getStatus().equalsIgnoreCase("locked") || user.getStatus().equalsIgnoreCase("lock")
					|| user.getStatus().equalsIgnoreCase("inactive")) {
				String errorMsg = "Your account locked kidly unlock";
				redirect.addFlashAttribute("errorMsg", errorMsg);
				return"redirect:loginhome";
			}
			String loginSuccMsg = "Login Successfull.";
			redirect.addFlashAttribute("loginSuccMsg", loginSuccMsg);
			String userName= user.getFname()+" "+user.getLname();
			ses.setAttribute("userName", userName);
			return "redirect:/registration/home";
		}
		String errorMsg = "Incorect password";
		ses.setAttribute("errorMsg", errorMsg);
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/login/error";
	}
	
	@GetMapping("/forgotpass")
	public String forgotPass(@ModelAttribute("frgt") CwAndAdPojo pojo) {
		return "case_workers_management/login/passwordrestpage";
	}
	
	@PostMapping("/postpasswordreset")
	public String restPasswored(@ModelAttribute("frgt") CwAndAdPojo pojo,
			                    HttpSession ses
			                    ) throws Exception {
		log.info(METHOD_EXECUTION_STARTED+"-restPasswored");
		CwAndAdPojo user1 = service.getuser(pojo.getEmailid());
		if(user1.getUserid()!=null) {
			boolean sentTempass = service.resetpassword(user1);
			if(sentTempass) {
				CwAndAdPojo user = service.getuser(user1.getEmailid());
				mail.sendMailForRestPawword(user);
				PhoneNumber number = new PhoneNumber("+91"+user.getPhnumber());
				int otp = RandomPassGenerator.otp(Integer.parseInt(env.getProperty(TEMPORARY_PASSWORD_LENGTH)));
				String body = "Your account reset otp(One time password) is "+otp+" Please do not share any one,"
						+ "use this for password reset";
				sms.sendSms(number, body);
				String mailId = user.getEmailid();
				String phoneNumber="+91"+"*******"+String.valueOf(user.getPhnumber()).substring(6);
				ses.setAttribute("otp", String.valueOf(otp));
				ses.setAttribute("mailid", mailId);
				ses.setAttribute("phno", phoneNumber);
				ses.setAttribute("tempasssentmail", mailId);
				log.info(METHOD_EXECUTION_ENDED);
				return"redirect:passwordResetUrl";
			}
			throw new IllegalAccessError();
		}
		throw new ExceptionInFounduser(USER_NOT_FOUND);
	}
	
	@GetMapping("/passwordResetUrl")
	public String passwordrestForm(@ModelAttribute("mdl") UnlockAccountPojo pojo,
			                       RedirectAttributes redirect) {
		
		return "case_workers_management/login/resetpasswordpage";
	}
	
	@PostMapping("/postOTPEntered")
	public String postEnterOtp(@ModelAttribute("mdl") UnlockAccountPojo pojo,
			                   HttpSession ses,
			                   RedirectAttributes redirect,
			                   Map<String, Object> map) {
		log.info(METHOD_EXECUTION_STARTED+"-postEnterOtp");
		String otp = (String)ses.getAttribute("otp");
		ses.removeAttribute("otp");
		String mailId = (String)ses.getAttribute(SES_MAILID);
		CwAndAdPojo user = service.getuser(mailId);
		if(pojo.getTempass().equals(user.getPwd()) && pojo.getOtp().equals(otp)) {
			log.info(METHOD_EXECUTION_ENDED);
			return "redirect:setpassword";
		}
		String errorOTP = "Entered otp are wrong";
		redirect.addFlashAttribute("errorOtp", errorOTP);
		return "redirect:passwordResetUrl";
	}
	
	@GetMapping("/setpassword")
	public String newPasswordSet(@ModelAttribute("model") UnlockAccountPojo pojo,
			                     HttpSession ses) {
		log.info(METHOD_EXECUTION_STARTED+"-newPasswordSet");
		String userMail = (String)ses.getAttribute("mailid");
		pojo.setMail(userMail);
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/login/addNewPassword";
	}
	
	@PostMapping("/postAddNewPassword")
	public String postSetNewPassword(@ModelAttribute("model") UnlockAccountPojo pojo,
			                          HttpSession ses) throws Exception {
		log.info(MTHOD_DEBUG_STARTED+"-postSetNewPassword");
		CwAndAdPojo user = service.getuser(pojo.getMail());
		user.setPwd(pass.encryption(pojo.getConfpassword()));
		String result = service.accountUpdate(user);
		ses.setAttribute("resetpasswordMsg", result);
		log.info(METHOD_EXECUTION_ENDED);
		return "redirect:susurl";
	}
	@GetMapping("/susurl")
	public String redirectUrl(Model model, HttpSession ses) {
		log.info(METHOD_EXECUTION_STARTED+"-redirectUrl");
		model.addAttribute("resultMsg", ses.getAttribute("resetpasswordMsg"));
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/login/passwordrestsuccesspage";
	}
}
