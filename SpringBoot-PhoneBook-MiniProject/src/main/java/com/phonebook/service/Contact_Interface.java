package com.phonebook.service;

import java.util.List;

import com.phonebook.model.Contact;


public interface Contact_Interface {

	//this method for submitting the contact
	boolean insertContact(Contact c);
	//get all contact records
	 List<Contact> getAllContact();
	 //for only one object
	Contact findContact(Integer Id);
	//update contact
	String deleteContact(Integer cid);
}
