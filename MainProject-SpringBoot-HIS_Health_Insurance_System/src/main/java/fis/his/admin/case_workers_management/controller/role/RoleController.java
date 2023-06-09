package fis.his.admin.case_workers_management.controller.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static fis.his.admin.case_workers_management.constant.LogConstant.*;
import fis.his.admin.case_workers_management.model.RolePojo;
import fis.his.admin.case_workers_management.service.role.RolesServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {

	@Autowired
	private RolesServiceInterface roleService;

	/**
	 * 
	 * @param pojo
	 * @return
	 */

	@GetMapping("/home")
	public String roleCreate(@ModelAttribute("roleM") RolePojo pojo) {
		return "case_workers_management/role/role$home";
	}

	/**
	 * 
	 * @param pojo
	 * @param redirect
	 * @return
	 */
	@PostMapping("/create")
	public String roleCreatePost(@ModelAttribute("roleM") RolePojo pojo, 
			                     RedirectAttributes redirect) {
		String status = roleService.checkRole(pojo.getRole());
		if (status != null) {
			if (status.equalsIgnoreCase("inactive")) {
				String result = ROLE_STATUS_INACTIVE;
				redirect.addFlashAttribute("resultMsg", result);
			} else if (status.equalsIgnoreCase("active")) {
				String result = ROLE_STATUS_ACTIVE;
				redirect.addFlashAttribute(ROLE_STATUS_REDIRECTMSG, result);
			}else {
			String result = roleService.createNewRole(pojo);
			redirect.addFlashAttribute(ROLE_STATUS_REDIRECTMSG, result);
			}
		}
		return "redirect:home";
	}
	@GetMapping("/roleCheck")
	@ResponseBody
	public String roleExistStatus(@RequestParam("roles") String role) {
		String response = roleService.checkRole(role);
		return response!=null? response.toUpperCase():"UNIQUE";
	}
}
