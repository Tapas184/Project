package fis.his.admin.case_workers_management.service.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.his.admin.case_workers_management.entity.EntityForRole;
import fis.his.admin.case_workers_management.model.RolePojo;
import fis.his.admin.case_workers_management.repository.RolesRepository;

@Service
public class RoleInterfaceImpl implements RolesServiceInterface {

	@Autowired
	private RolesRepository rolerepo;

	@Override
	public List<String> getRoleList() {
		List<EntityForRole> roleList = rolerepo.findAll();
		return roleList.stream().map(s -> s.getRole()).toList();
	}

	@Override
	public String createNewRole(RolePojo pojo) {
		if (pojo.getRoleid() == null) {
			EntityForRole role = new EntityForRole();
			role.setRole(pojo.getRole().toUpperCase());
			EntityForRole saveEntity = rolerepo.save(role);
			return saveEntity.getRole() + " Role created with id :" + saveEntity.getRoleid();

		}
		return null;
	}

	@Override
	public String checkRole(String role) {
		Optional<EntityForRole> opt = rolerepo.findByRole(role.toUpperCase());
		if (opt.isPresent()) {
			EntityForRole rol = opt.get();
			if (rol.getRoleStatus().equalsIgnoreCase("inactive")) {
				return "inactive";
			} else if (rol.getRoleStatus().equalsIgnoreCase("active")) {
				return "active";
			}
		}
		return null;

	}
}
