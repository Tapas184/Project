package fis.his.admin.planmngmt.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class PlanModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7108842377654916973L;
	private Integer planId;
	private String planName;
	private String planDescription;
	private Character planStatus;
	private String startDate;
	private String endDate;
}
