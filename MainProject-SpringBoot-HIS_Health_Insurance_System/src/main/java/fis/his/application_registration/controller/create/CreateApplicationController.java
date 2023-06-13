package fis.his.application_registration.controller.create;

import java.time.LocalDate;
import java.time.ZoneId;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import  fis.his.application_registration.constant.Constant;
import fis.his.application_registration.model.ARModel;
import fis.his.application_registration.model.SSNModel;
import fis.his.application_registration.service.ARServiceInterface;

@Controller
@RequestMapping(value = {"/ar"})
public class CreateApplicationController {

	@Autowired
	private ARServiceInterface service;
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objMapper;

	@Value("${ssn.statename}")
	private String state;
	
	@GetMapping(value = {"/ssncheck"})
	public String showSsnHome(@ModelAttribute("model") ARModel model) {
		return "ARModule/Create/ssncheck";
	}
	
	@GetMapping("/fetchSSn")
	public String  submitSsnForm(@ModelAttribute("model") ARModel model,
			HttpSession ses) {
		SSNModel mode =(SSNModel)ses.getAttribute("modl");
		BeanUtils.copyProperties(mode, model);
        LocalDate date = LocalDate.ofInstant(mode.getDob().toInstant(), ZoneId.systemDefault());
             String[] split = date.toString().split("-");
        model.setDob(split[2]+"-"+split[1]+"-"+split[0]);	
		model.setSsn(String.valueOf(mode.getSsn()));
		if(mode.getGender().equalsIgnoreCase("Male")) {
    		model.setGender('M');
    	}else
    		model.setGender('F');
		return "ARModule/Create/applicationreg";
	}
	
	
	@PostMapping(value = {"/postcreate"})
	public String checkSSN(@ModelAttribute("model") ARModel model,
			               RedirectAttributes redirect,
			               HttpSession ses) throws JsonProcessingException {
		
		String producerUrl = "http://localhost:4040/ssn/getUser/"+model.getSsn();
		ResponseEntity<String> resp = restTemplate.exchange(producerUrl, HttpMethod.GET, null, String.class);
		String jsonRespBody = resp.getBody();
		    SSNModel ssnModel = objMapper.readValue(jsonRespBody, SSNModel.class);
		    if(ssnModel.getStateName().equalsIgnoreCase(state)) {
		    	String validCitizen= "valid citizen,u can process further";
		    	ses.setAttribute("modl", ssnModel);
		    	redirect.addFlashAttribute("validCitizen", validCitizen);
		    	return "redirect:fetchSSn" ;
		    }else {
		    	String invalidCitizen="You are not valid citizen for"+state;
		    	redirect.addFlashAttribute("invalidCitizen", invalidCitizen);
		    }
		return Constant.REDIRECT_CHECK;
	}
	
	@PostMapping("/postappform")
	public String submitApplicationForm(@ModelAttribute("model") ARModel model,
			                           RedirectAttributes redirect) {
		String result = service.createApplication(model);
		redirect.addFlashAttribute("result", result);
		return "redirect:/arview/showApplications";
	}
	
}
