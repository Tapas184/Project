package fis.ssn.restcontoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fis.ssn.service.InterfaceSsnService;

@RestController
@RequestMapping("/requestState")
public class SsnStateRestController {
	
	@Autowired
	private InterfaceSsnService service;
	
	@GetMapping("/ssnstate")
	public List<String> getSsnState(){
		return service.fetchAllState();
	}

}
