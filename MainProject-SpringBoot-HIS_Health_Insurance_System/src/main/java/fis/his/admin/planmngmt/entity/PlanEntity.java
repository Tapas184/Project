package fis.his.admin.planmngmt.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "FIS_HIS_ADMIN_PLAN_DB")
public class PlanEntity implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -782442924623179828L;
	@Id
	@SequenceGenerator(name = "Plan_seq",sequenceName = "plan_seq_gen",initialValue = 100,allocationSize = 1)
	@GeneratedValue(generator = "Plan_seq",strategy = GenerationType.SEQUENCE)
	@Column(name = "PLAN_ID")
	private Integer planId;
	
	@Column(name = "PLAN_NAME",unique = true)
	private String planName;
	
	@Column(name = "PLAN_DESCRIPTION")
	private String planDescription;
	
	@Column(name = "PLAN_STATUS",length = 1)
	private Character planStatus;
	
	@Column(name = "PLAN_START_DATE")
	@DateTimeFormat(pattern = "dd-MM-YYYY")
	private LocalDate startDate;
	
	@Column(name = "PLAN_END_DATE")
	@DateTimeFormat(pattern = "dd-MM-YYYY")
	private LocalDate endDate;
}
