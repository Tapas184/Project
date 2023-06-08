package fis.his.case_workers_management.controller.edit;

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

import fis.his.case_workers_management.model.CwAndAdPojo;
import fis.his.case_workers_management.service.adminandcw.AdminAndCwServiceInterface;
import fis.his.case_workers_management.service.role.RolesServiceInterface;

@Controller
@RequestMapping("/admin$cw$edit")
public class AdminAndCwAccountEditController {
	
	@Autowired
	private AdminAndCwServiceInterface service;
	
	@Autowired
	private RolesServiceInterface roleService;
	
	@GetMapping("/getalldetails")
	public String getAllUser(Map<String, Object> map) {
		List<CwAndAdPojo> userList = service.getAllData();
		map.put("userlist", userList);
		return "case_workers_management/editjsp/getalldata";
	}
	
	@GetMapping("/edit")
	public String methodForEditUser(@RequestParam("id") Integer id,
			                        HttpSession ses,
			                        Map<String, Object> map) {
		CwAndAdPojo user = service.getUserById(id);
		if(user!=null) {
			List<String> roleList = roleService.getRoleList();
			map.put("editAttribute", user);
			map.put("rolesList", roleList);
			return "case_workers_management/editjsp/editpage";
		}
		return null;
	}
	
	@PostMapping("/postedit")
	public String postEdit(@ModelAttribute("editAttribute")CwAndAdPojo pojo,
			               RedirectAttributes redirect) {
		String result = service.postEditAccountUpdate(pojo);
		redirect.addFlashAttribute("editResult", result);
		return "redirect:getalldetails";
	}
	
	@GetMapping("/unlock")
	public String unlockAccount(@RequestParam("id") Integer id,
			                    RedirectAttributes redirect) throws Exception {
		String result = service.unlockAccount(id);
		if(result!=null) {
			redirect.addFlashAttribute("unlocksuccessmsg", result);
			return"redirect:getalldetails";
		}
		String unlockErrorMsg = "Problem in sending mail";
		redirect.addFlashAttribute("unlockerrormsg", unlockErrorMsg);
		return "redirect:getalldetails";
	}
}
