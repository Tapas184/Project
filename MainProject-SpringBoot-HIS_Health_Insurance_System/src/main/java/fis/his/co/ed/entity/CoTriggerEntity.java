package fis.his.co.ed.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "HIS_TRIGGER_DB")
public class CoTriggerEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3833649041674603044L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Tri_Id")
	private Long triggerId;
	
	@Column(name = "Case_Id")
	private Long casenumber;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreateDate",updatable = false)
	private Date createDate;
	
	@Column(name = "Trigger_Status")
	private String trigStatue;
	
	@Column(name = "Update_Date")
	private Date updateDate;
}
