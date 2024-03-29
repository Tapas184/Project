package fis.his.admin.case_workers_management.controller.edit;

import static fis.his.admin.case_workers_management.constant.LogConstant.MAIL_SENT_FAILD_MSG;
import static fis.his.admin.case_workers_management.constant.LogConstant.METHOD_EXECUTION_STARTED;
import static fis.his.admin.case_workers_management.constant.LogConstant.REDIRECT_GETALLCW_ADMIN_LIST;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fis.his.admin.case_workers_management.customexception.ExceptionInAccountActive;
import fis.his.admin.case_workers_management.customexception.ExceptionInSetAccountInactive;
import fis.his.admin.case_workers_management.entity.EntityForAdmin;
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
	 * Showing All user details by pageable
	 * @param map
	 * @param req
	 * @return
	 */
	@GetMapping("/getalldetails")
	public String getAllUser(Map<String, Object> map,
			                 HttpServletRequest req,
			                 @PageableDefault(direction = Direction.ASC,page = 0,size = 3,sort = "fname") Pageable pageable) {
		log.info(METHOD_EXECUTION_STARTED + "-getAllUser");
		//List<CwAndAdPojo> userList = service.getAllData();
		Page<EntityForAdmin> page = service.findAllDetails(pageable);
		map.put("page", page);
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
