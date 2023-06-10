package fis.his.admin.case_workers_management.controller.edit;

import static fis.his.admin.case_workers_management.constant.LogConstant.*;

import java.util.List;
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

import fis.his.admin.case_workers_management.customexception.ExceptionInAccountActive;
import fis.his.admin.case_workers_management.customexception.ExceptionInSetAccountInactive;
import fis.his.admin.case_workers_management.model.CwAndAdPojo;
import fis.his.admin.case_workers_management.service.adminandcw.AdminAndCwServiceInterface;
import fis.his.admin.case_workers_management.service.role.RolesServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin$cw$edit")
public class AdminAndCwAccountEditController {

	@Autowired
	private AdminAndCwServiceInterface service;

	@Autowired
	private RolesServiceInterface roleService;

	/**
	 * 
	 * @param map
	 * @return
	 */
	@GetMapping("/getalldetails")
	public String getAllUser(Map<String, Object> map) {
		log.info(METHOD_EXECUTION_STARTED + "-getAllUser");
		List<CwAndAdPojo> userList = service.getAllData();
		map.put("userlist", userList);
		return "case_workers_management/editjsp/getalldata";
	}

	/**
	 * 
	 * @param id
	 * @param ses
	 * @param map
	 * @return
	 */

	@GetMapping("/edit")
	public String methodForEditUser(@RequestParam("id") Integer id, HttpSession ses, Map<String, Object> map) {
		CwAndAdPojo user = service.getUserById(id);
		if (user != null) {
			List<String> roleList = roleService.getRoleList();
			map.put("editAttribute", user);
			map.put("rolesList", roleList);
			return "case_workers_management/editjsp/editpage";
		}
		return null;
	}

	/**
	 * 
	 * @param pojo
	 * @param redirect
	 * @return
	 */

	@PostMapping("/postedit")
	public String postEdit(@ModelAttribute("editAttribute") CwAndAdPojo pojo, RedirectAttributes redirect) {
		String result = service.postEditAccountUpdate(pojo);
		redirect.addFlashAttribute("editResult", result);
		return REDIRECT_GETALLCW_ADMIN_LIST;
	}

	/**
	 * 
	 * @param id
	 * @param redirect
	 * @return
	 */
	@GetMapping("/unlock")
	public String unlockAccount(@RequestParam("id") Integer id, RedirectAttributes redirect) {
		String result = service.unlockAccount(id);
		if (result != null) {
			redirect.addFlashAttribute("unlocksuccessmsg", result);
		} else {
			String unlockErrorMsg = MAIL_SENT_FAILD_MSG;
			redirect.addFlashAttribute("unlockerrormsg", unlockErrorMsg);
		}
		return REDIRECT_GETALLCW_ADMIN_LIST;
	}

	/**
	 * 
	 * @param id
	 * @param redirect
	 * @return
	 */
	@GetMapping("/delete")
	public String deleteAccount(@RequestParam("id") Integer id, RedirectAttributes redirect) {
		try {
			String deleteAccountMsg = service.accountSetInactive(id);
			redirect.addFlashAttribute("deleteAccountMsg", deleteAccountMsg);
		} catch (ExceptionInSetAccountInactive e) {
			e.getMessage();
		}
		return REDIRECT_GETALLCW_ADMIN_LIST;
	}

	/**
	 * 
	 * @param id
	 * @param redirect
	 * @return
	 */
	@GetMapping("/active")
	public String activeAccount(@RequestParam("id") Integer id, RedirectAttributes redirect) {
		try {
			String activeAccountMsg = service.accountActivate(id);
			redirect.addFlashAttribute("activeAccountMsg", activeAccountMsg);
		} catch (ExceptionInAccountActive e) {
			e.getMessage();
		}
		return REDIRECT_GETALLCW_ADMIN_LIST;
	}
}
