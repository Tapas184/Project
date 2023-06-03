package fis.ssn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fis.ssn.entity.SsnEntity;
import fis.ssn.entity.SsnState;
import fis.ssn.exception.UserNotFoundException;
import fis.ssn.model.SsnPojo;
import fis.ssn.repository.SsnRepository;
import fis.ssn.repository.SsnStateRepository;

@Service("ssnService")
public class SsnServiceImpl implements InterfaceSsnService {

	@Autowired
	private SsnRepository ssnRepo;
	@Autowired
	private SsnStateRepository ssnStateRepo;

	@Override
	public String createSsn(SsnPojo user) {
		try {
			SsnEntity targetUser = new SsnEntity();
			BeanUtils.copyProperties(user, targetUser);
			if (user.getSsn() == null) {
				SsnEntity user1 = ssnRepo.save(targetUser);
				return "Ssn created successfully uniquenumber is :" + user1.getSsn();
			} else {
				SsnEntity user1 = ssnRepo.save(targetUser);
				return "User Details has been updated, User Name :" + user1.getFname();
			}

		} catch (Exception e) {
			return "Error: Problem in creation ssn(" + e.getMessage() + ") try again";
		}

	}

	@Override
	public SsnPojo fetchUserDetails(Long ssn) throws UserNotFoundException {
		Optional<SsnEntity> user = ssnRepo.findById(ssn);
		SsnPojo pojouser = new SsnPojo();
		if (user.isPresent()) {
			BeanUtils.copyProperties(user.get(), pojouser);
			return pojouser;
		}

		throw new UserNotFoundException("User Not Found on this ssn ");

	}

	@Override
	public List<String> fetchAllState() {
		return ssnStateRepo.findAllState();
	}

	@Override
	public Optional<SsnState> findState(Integer id) {
		return ssnStateRepo.findById(id);
	}
}// class
