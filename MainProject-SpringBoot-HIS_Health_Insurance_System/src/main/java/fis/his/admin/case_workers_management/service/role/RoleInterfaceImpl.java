package fis.his.admin.case_workers_management.service.role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
			Optional<EntityForRole> opt = rolerepo.findById(pojo.getRoleid());
			 if(opt.isPresent()) {
				 EntityForRole entity = opt.get();
				 entity.setRoleStatus("ACTIVE");
				 rolerepo.save(entity);
			 }
		return "Roles Updated";
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
	
	@Override
	public List<RolePojo> getAllRoles() {
		List<EntityForRole> entitylist = rolerepo.findAll();
		List<RolePojo> pojoList = new ArrayList<>();
		entitylist.stream().forEach(s->{
			RolePojo p = new RolePojo();
			BeanUtils.copyProperties(s, p);
			pojoList.add(p);
		});
		return pojoList;
	}
	@Override
	public RolePojo getRoleById(Integer id) {
		Optional<EntityForRole> opt = rolerepo.findById(id);
		if(opt.isPresent()) {
			RolePojo pojo = new RolePojo();
			BeanUtils.copyProperties(opt.get(), pojo);
			return pojo;
		}
		return null;
	}
	
	@Override
	public void inactiveRole(Integer id) {
		rolerepo.deleteById(id);
	}
	
	@Override
	public Page<EntityForRole> findAllRole(Pageable pageable) {
		return rolerepo.findAll(pageable);
	}
}
