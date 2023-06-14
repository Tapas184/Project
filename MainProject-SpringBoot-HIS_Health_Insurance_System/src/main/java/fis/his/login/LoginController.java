package fis.his.login;

import static fis.his.admin.case_workers_management.constant.LogConstant.METHOD_EXECUTION_ENDED;
import static fis.his.admin.case_workers_management.constant.LogConstant.METHOD_EXECUTION_STARTED;
import static fis.his.admin.case_workers_management.constant.LogConstant.MTHOD_DEBUG_STARTED;
import static fis.his.admin.case_workers_management.constant.LogConstant.SES_MAILID;
import static fis.his.admin.case_workers_management.constant.LogConstant.TEMPORARY_PASSWORD_LENGTH;
import static fis.his.admin.case_workers_management.constant.LogConstant.USER_NOT_FOUND;
import static fis.his.login.constant.LoginConstacnt.LOGIN_ERROR_MSG;

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
@RequestMapping(value = { "/userlogin", "/" })
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

	/**
	 * This method for showing login home page
	 * @param pojo : Model class used
	 * @return :String[LVN]
	 */
	@GetMapping(value = { "/loginhome", "/" })
	public String loginHome(@ModelAttribute("logindata") CwAndAdPojo pojo) {
		log.info(METHOD_EXECUTION_STARTED + " loginHome");
		return "case_workers_management/login/homepage";
	}
	/**
	 * {@summary : This method used for filter all the level of a user then it allow to login}
	 * @param pojo : model class is used
	 * @param ses :Sees interface is used
	 * @param redirect :As per role this method is redirect to accordingly
	 * @return : String[LVN]
	 * @throws Exception
	 */

	@PostMapping("/postlogin")
	public String postLoginForm(@ModelAttribute("logindata") CwAndAdPojo pojo,
			                 HttpSession ses,
			                 RedirectAttributes redirect
			                    ) throws Exception {
		log.info(METHOD_EXECUTION_STARTED + " postLogin");
		String errorMsg = null;
		CwAndAdPojo user = service.getuser(pojo.getEmailid());
		if (user != null) {
			if (pojo.getPwd().equals(pass.decryption(user.getPwd()))) {
				if (!(user.getStatus().equalsIgnoreCase("locked") || user.getStatus().equalsIgnoreCase("lock"))) {
					if (!user.getStatus().equalsIgnoreCase("inactive")) {
						if(user.getRole().equalsIgnoreCase("admin")) {
							
							String loginSuccMsg = "Login Successfull.";
							redirect.addFlashAttribute("loginSuccMsg", loginSuccMsg);
							String userName= user.getFname()+" "+user.getLname();
							ses.setAttribute("userName", userName);
							ses.setAttribute("userRole", user.getRole());
							return "redirect:/registration/adminhome";
							
						}else if(user.getRole().equalsIgnoreCase("cw")) {
							String loginSuccMsg = "Login Successfull.";
							redirect.addFlashAttribute("loginSuccMsg", loginSuccMsg);
							String userName= user.getFname()+" "+user.getLname();
							ses.setAttribute("userName", userName);
							ses.setAttribute("userRole", user.getRole());
							return "redirect:/registration/cwhome";
						}//else
						
					}else {
					errorMsg = "Your account is inactive kindly contact admin";
					redirect.addFlashAttribute(LOGIN_ERROR_MSG, errorMsg);
					}
				}
				else {
				errorMsg = "Account is locked please unlock";
				redirect.addFlashAttribute("errorMsg", errorMsg);
				}
			}
			else {
			errorMsg = "Wrong password";
			redirect.addFlashAttribute(LOGIN_ERROR_MSG, errorMsg);
			}
		} // 1st if
		else {
			errorMsg = "Invalid mailid";
			redirect.addFlashAttribute(LOGIN_ERROR_MSG, errorMsg);
		} // 1st else
		log.info(METHOD_EXECUTION_ENDED);
		return "redirect:loginhome";
	}

	/**
	 * {@summary: This method is used to user can reset his password by clicking forgot password }
	 * @param pojo
	 * @return
	 */
	@GetMapping("/forgotpass")
	public String forgotPass(@ModelAttribute("frgt") CwAndAdPojo pojo) {
		return "case_workers_management/login/passwordrestpage";
	}

	/**
	 * {@summary: This method used for after user click forgot password this method will verify the otp of mail and mobile}
	 * @param pojo : Model class
	 * @param ses :Interface
	 * @return :String lVN
	 * @throws Exception : User not found exception.
	 */
	@PostMapping("/postpasswordreset")
	public String restPasswored(@ModelAttribute("frgt") CwAndAdPojo pojo, HttpSession ses) throws Exception {
		log.info(METHOD_EXECUTION_STARTED + "-restPasswored");
		CwAndAdPojo user1 = service.getuser(pojo.getEmailid());
		if (user1.getUserid() != null) {
			boolean sentTempass = service.resetpassword(user1);
			if (sentTempass) {
				CwAndAdPojo user = service.getuser(user1.getEmailid());
				mail.sendMailForRestPawword(user);
				PhoneNumber number = new PhoneNumber("+91" + user.getPhnumber());
				int otp = RandomPassGenerator.otp(Integer.parseInt(env.getProperty(TEMPORARY_PASSWORD_LENGTH)));
				String body = "Your account reset otp(One time password) is " + otp + " Please do not share any one,"
						+ "use this for password reset";
				sms.sendSms(number, body);
				String mailId = user.getEmailid();
				String phoneNumber = "+91" + "*******" + String.valueOf(user.getPhnumber()).substring(6);
				ses.setAttribute("otp", String.valueOf(otp));
				ses.setAttribute("mailid", mailId);
				ses.setAttribute("phno", phoneNumber);
				ses.setAttribute("tempasssentmail", mailId);
				log.info(METHOD_EXECUTION_ENDED);
				return "redirect:passwordResetUrl";
			}
			throw new IllegalAccessError();
		}
		throw new ExceptionInFounduser(USER_NOT_FOUND);
	}
	
	/**
	 * {@summary: This method is used to display the page where user can insert mail and phone otp}
	 * @param pojo: Model object
	 * @return : LVN
	 */
	@GetMapping("/passwordResetUrl")
	public String passwordrestForm(@ModelAttribute("mdl") UnlockAccountPojo pojo) {

		return "case_workers_management/login/resetpasswordpage";
	}

	/**
	 * {@summary: This method used for after user submitting otp it will check otp is valid or not}
	 * @param pojo :Model object
	 * @param ses :Interface
	 * @param redirect : /passwordResetUrl
	 * @return String[LVN]
	 */
	@PostMapping("/postOTPEntered")
	public String postEnterOtp(@ModelAttribute("mdl") UnlockAccountPojo pojo, HttpSession ses,
			RedirectAttributes redirect) {
		log.info(METHOD_EXECUTION_STARTED + "-postEnterOtp");
		String otp = (String) ses.getAttribute("otp");
		ses.removeAttribute("otp");
		String mailId = (String) ses.getAttribute(SES_MAILID);
		CwAndAdPojo user = service.getuser(mailId);
		if (pojo.getTempass().equals(user.getPwd()) && pojo.getOtp().equals(otp)) {
			log.info(METHOD_EXECUTION_ENDED);
			return "redirect:setpassword";
		}
		String errorOTP = "Entered otp are wrong";
		redirect.addFlashAttribute("errorOtp", errorOTP);
		return "redirect:passwordResetUrl";
	}

	/**
	 * {@summary: This method is used for set the password}
	 * @param pojo : Model object
	 * @param ses : Interface
	 * @return : String LVN
	 */
	@GetMapping("/setpassword")
	public String newPasswordSet(@ModelAttribute("model") UnlockAccountPojo pojo, HttpSession ses) {
		log.info(METHOD_EXECUTION_STARTED + "-newPasswordSet");
		String userMail = (String) ses.getAttribute("mailid");
		pojo.setMail(userMail);
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/login/addNewPassword";
	}

	/**
	 * {@summary: This method is used after user submit new password}
	 * @param pojo : Model object
	 * @param ses :interface
	 * @return : String LVN
	 * @throws Exception
	 */
	@PostMapping("/postAddNewPassword")
	public String postSetNewPassword(@ModelAttribute("model") UnlockAccountPojo pojo, HttpSession ses)
			throws Exception {
		log.info(MTHOD_DEBUG_STARTED + "-postSetNewPassword");
		CwAndAdPojo user = service.getuser(pojo.getMail());
		user.setPwd(pass.encryption(pojo.getConfpassword()));
		String result = service.accountUpdate(user);
		ses.setAttribute("resetpasswordMsg", result);
		log.info(METHOD_EXECUTION_ENDED);
		return "redirect:susurl";
	}
/**
 * {@summary: This method is used for after successfully changed password}
 * @param model: Model object
 * @param ses :Interface
 * @return :LVN
 */
	@GetMapping("/susurl")
	public String redirectUrl(Model model, HttpSession ses) {
		log.info(METHOD_EXECUTION_STARTED + "-redirectUrl");
		model.addAttribute("resultMsg", ses.getAttribute("resetpasswordMsg"));
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/login/passwordrestsuccesspage";
	}
}
