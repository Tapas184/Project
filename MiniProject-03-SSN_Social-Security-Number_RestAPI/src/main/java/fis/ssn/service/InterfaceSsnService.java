package fis.ssn.service;

import java.util.List;
import java.util.Optional;

import fis.ssn.entity.SsnState;
import fis.ssn.model.SsnPojo;

public interface InterfaceSsnService {

	public String createSsn(SsnPojo user);

	public SsnPojo fetchUserDetails(Long ssn);
	
	public List<String> fetchAllState();
	
	public Optional<SsnState>  findState(Integer id);
}
