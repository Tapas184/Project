package fis.his.application_registration.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ARModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8767594520127608599L;

	private Integer id;
	
	private String fname;
	
	private String lname;
	
	private String dob;
	
	private Character gender;
	
	private String ssn;
	
	private String phone;
	
	private String mail;
	
	private String stateName;
	
	private String createdDate;
	
	private String lastUpdate;
}
