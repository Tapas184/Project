package fis.ssn.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "SSN-Model")
public class SsnPojo {
	@ApiModelProperty(notes = "Hold Ssn Number",
			          allowEmptyValue = false,
			          required = true,
			          value = "Long",
			          example = "<9 digit>")
	private Long ssn;
	@ApiModelProperty(notes = "Hold Citizen First name",
			          value = "String",
			           required = false)
	private String fname;
	
	@ApiModelProperty(notes = "Hold Citizen Last Name",
			          value = "String",
			          required = false)
	private String lname;
	
	@ApiModelProperty(notes = "Hold Citizen Gender",
			          value = "String",
			          example = "Y/N",
			          required = false)
	private String gender;
	
	@ApiModelProperty(notes = "Hold Citizen date of birth",
			          value = "java.utils.Date",
			          example = "yyyy-MM-dd",
			          required = false)
	private Date dob;
	
	@ApiModelProperty(notes = "Holde Citizen state name",
			          value = "String",
			          required = false)
	private String stateName;
}
