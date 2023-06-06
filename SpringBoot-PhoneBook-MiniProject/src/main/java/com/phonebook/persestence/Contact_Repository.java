package com.phonebook.persestence;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phonebook.entity.ContactEntity;

public interface Contact_Repository extends JpaRepository<ContactEntity, Serializable> {
	//Recommended to use Serializable instead of use specific typer

}
