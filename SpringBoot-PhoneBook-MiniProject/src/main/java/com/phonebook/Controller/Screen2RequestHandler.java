package com.phonebook.Controller;

import java.util.List;
//import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.phonebook.model.Contact;
import com.phonebook.service.Contact_Interface;

@Controller
public class Screen2RequestHandler {
	@Autowired
	private Contact_Interface service;
	
	//request for showing all contact details
	@GetMapping("/showAllContact")
	public String showAllContact(Model model) {
		//call the business method and keep them in Map object
	List<Contact> contactList=service.getAllContact();
	//add contact list in to Map
	model.addAttribute("list", contactList);
	//return logical view name
		return "allcontactdetails";
	}//showAllContact
	
	@GetMapping("/edit")
	public String editContact(@RequestParam("cid") Integer cid ,
			                  Model model) {
		//call the business method
		Contact findContact = service.findContact(cid);
		model.addAttribute("contact", findContact);
		return "regContact";
	}//editContact
	
	@GetMapping("/delete")
	public String deleteContact(@RequestParam("cid") Integer id,
			                     HttpSession ses) {
		String result=service.deleteContact(id);
		ses.setAttribute("deletemsg", result);
		ses.setMaxInactiveInterval(5);
		return "redirect:showAllContact";
	}
}
