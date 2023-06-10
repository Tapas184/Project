package fis.his.admin.case_workers_management.controller.role;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static fis.his.admin.case_workers_management.constant.LogConstant.*;
import fis.his.admin.case_workers_management.model.RolePojo;
import fis.his.admin.case_workers_management.service.role.RolesServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/viewRoles")
@Slf4j
public class ViewRolesController {

	@Autowired
	private RolesServiceInterface roleService;

	/**
	 * {@summary : This method is used for display all roles}
	 * 
	 * @param map : ModelAndView Attributes
	 * @return : List<RolePojo>
	 */
	@GetMapping("/showRoles")
	public String getAllRoles(Map<String, Object> map) {
		log.info(METHOD_EXECUTION_STARTED + "-getAllRoles");
		List<RolePojo> roleList = roleService.getAllRoles();
		map.put("roleList", roleList);
		log.info(METHOD_EXECUTION_ENDED);
		return "case_workers_management/role/viewroles";
	}

	/**
	 * {@summary : This method is used for inactive role which is active}
	 * 
	 * @param id : Integer
	 * @return :redirect to 'showRoles'
	 */
	@GetMapping("/inactive")
	public String inactiveRole(@RequestParam("id") Integer id) {
		log.info(METHOD_EXECUTION_STARTED + "-inactiveRole");
		roleService.inactiveRole(id);
		log.info(METHOD_EXECUTION_ENDED);
		return "redirect:showRoles";
	}

	/**
	 * {@summary : This method us used for activate , Inactive roles}
	 * 
	 * @param id :Integer
	 * @return :Redirect to 'showRoles'
	 */
	@GetMapping("/active")
	public String activeRole(@RequestParam("id") Integer id) {
		log.info(METHOD_EXECUTION_STARTED + "-activeRole");
		RolePojo pojo = roleService.getRoleById(id);
		roleService.createNewRole(pojo);
		log.info(METHOD_EXECUTION_ENDED);
		return "redirect:showRoles";
	}
}
