package fis.ssn.restcontoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fis.ssn.exception.UserNotFoundException;
import fis.ssn.model.SsnPojo;
import fis.ssn.service.InterfaceSsnService;

@RestController
@RequestMapping("ssn")
public class SsnRestController {
	@Autowired
	private InterfaceSsnService service;
	
	@PostMapping("/generateSsn")
	public ResponseEntity<String> submitRegistrationForm(@RequestBody SsnPojo user){
		try {
			String result = service.createSsn(user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
		}
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<Object> getUserDetails(@PathVariable("id") Long id) throws UserNotFoundException
	{
		SsnPojo user = service.fetchUserDetails(id);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	@PatchMapping("/edit")
	public ResponseEntity<Object> userEditDate(@RequestBody SsnPojo user){
		String result = service.createSsn(user);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}//class
