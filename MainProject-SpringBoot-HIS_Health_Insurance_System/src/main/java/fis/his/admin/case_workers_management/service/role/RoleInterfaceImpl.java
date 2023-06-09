package fis.his.admin.case_workers_management.service.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.admin.case_workers_management.entity.EntityForRole;
import fis.his.admin.case_workers_management.repository.RolesRepository;

@Service
public class RoleInterfaceImpl implements RolesServiceInterface {

	@Autowired
	private RolesRepository rolerepo;
	
	@Override
	public List<String> getRoleList() {
		List<EntityForRole> roleList = rolerepo.findAll();
		return roleList.stream()
		        .map(s->s.getRole())
		        .toList();
	}

}
