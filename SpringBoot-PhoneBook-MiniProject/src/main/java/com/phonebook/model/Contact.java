package com.phonebook.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@SuppressWarnings("serial")
@Data
public class Contact implements Serializable {

	private Integer cid;
	private String name;
	private String email;
	private Long phoneNo;
	private Date insertdate;
	private Date updatedate;
}
