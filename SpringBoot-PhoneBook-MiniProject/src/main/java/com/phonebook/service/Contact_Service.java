package com.phonebook.service;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.phonebook.entity.ContactEntity;
import com.phonebook.model.Contact;
import com.phonebook.persestence.Contact_Repository;

@Service("phoneService")
//to make this class for java bean
public class Contact_Service implements Contact_Interface {

//Inject repository 
	@Autowired // for automatic inject by spring bean
	private Contact_Repository repo;

	@Override
	public boolean insertContact(Contact c) {
		ContactEntity entity = new ContactEntity();
		BeanUtils.copyProperties(c, entity);
		ContactEntity savedEntity = repo.save(entity);

		return savedEntity.getCid() != null;
	}

	@Override
	public List<Contact> getAllContact() {
		// call the business method
		List<ContactEntity> contactEntityList = repo.findAll(Sort.by("name"));
		// we get the contact details in the form of Contact entity
		// create contact list collection and convert

		/*	List<Contact> contactList = new ArrayList<>();
		 * contactEntityList.forEach(s->{
				Contact c = new Contact();
				BeanUtils.copyProperties(s, c);
				c.setCid(s.getCid());
				c.setName(s.getName());
				c.setEmail(s.getEmail());
				contactList.add(c);
			});*/
		return contactEntityList.stream().map(entity -> {
			Contact c = new Contact();
			BeanUtils.copyProperties(entity, c);
			return c;
		}).collect(Collectors.toList());

	}
//(Find contact by it's name)

	@Override
	public Contact findContact(Integer Id) {
		// get the Entity object bi ID
		ContactEntity entity = repo.getReferenceById(Id);
		// create a normal PoJO contact object
		Contact contact = new Contact();
		// copy Contact Entity class object to POJO Object
		BeanUtils.copyProperties(entity, contact);
		// return object
		return contact;
	}// findContact()

	@Override
		public String deleteContact(Integer cid) {
		Optional<ContactEntity> opt = repo.findById(cid);
		//entity object 
		ContactEntity entity =null;
		//check is object is present or not
		  if(opt.isPresent()) {
			  //get the enitiy object
			  entity = opt.get();
			  //delete the  object
			  repo.deleteById(cid);
			  return entity.getName()+" deleted SucessFully";
		  }
		  else
			  return cid+" Id not availabe for delet";
		   
			
		}

}
