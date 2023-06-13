package fis.his.application_registration.controller.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fis.his.application_registration.model.ARModel;
import fis.his.application_registration.service.ARServiceInterface;

@Controller
@RequestMapping("/arview")
public class ViewApplicationController {

	@Autowired
	private ARServiceInterface service;
	
	@GetMapping("/showApplications")
	public String viewApplications(Map<String, Object> map) {
		List<ARModel> listOfModel = service.allApplication();
		map.put("listOfApplications", listOfModel);
		return"ARModule/view/viewapplications";
	}
	
	@GetMapping("/searchApp")
	public String searchApplication(@ModelAttribute("model") ARModel model) {
		return "ARModule/view/searchApplication";
	}
	@PostMapping("/fetchApplication")
	public String fetchApplication(@ModelAttribute("model") ARModel model,
			                       HttpSession ses) {
		ARModel user = service.fetchApplication(model.getId());
		if(user!=null) {
			ses.setAttribute("user", user);
		}
		return "redirect:showapplicationdetails";
	}
	
	@GetMapping("/showapplicationdetails")
	public String showDetails(HttpSession ses,
			                  Map<String, Object> map) {
		ARModel model = (ARModel)ses.getAttribute("user");
		map.put("mdl", model);
		return "ARModule/view/applicationdetails";
	}
}
