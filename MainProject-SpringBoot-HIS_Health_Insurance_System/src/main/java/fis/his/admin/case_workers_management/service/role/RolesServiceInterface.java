package fis.his.admin.case_workers_management.service.role;

import java.util.List;

import fis.his.admin.case_workers_management.model.RolePojo;

public interface RolesServiceInterface {
	
	public List<String> getRoleList();
	
	public String createNewRole(RolePojo pojo);
	
	public String checkRole(String role);
}
