package com.phonebook.Controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.phonebook.model.Contact;
import com.phonebook.service.Contact_Interface;

@Controller
public class Screen1Handler {
	
	@Autowired
	//Inject the service interface through Autowired
	private Contact_Interface service;
	
	@GetMapping("/")
	public String showHome() {
		//Return Logical view name
		return"home";
	}//For home page

	@GetMapping("/addContact")
	public String showAddContactPage(Model model) {
		//create a contact Object
		Contact c = new Contact();
		//add the contact object in to model Object
		model.addAttribute("contact", c);
		//return logical view name
		return "regContact";
	}
	
	@PostMapping("/addcontact")
	public String contactRegistered(@ModelAttribute("contact") Contact c, 
			HttpSession ses) {
		//call the service class method
	boolean status=	service.insertContact(c);
	//check the status true of false
	ses.setMaxInactiveInterval(7);
	if(c.getCid()!=null)
		ses.setAttribute("msg", "Conatct Updated");
	else if(c.getCid()==null && status==true)
		ses.setAttribute("msg", "Contact saved");
	return "redirect:addContact";
	}
}
