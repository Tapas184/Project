package fis.his.case_workers_management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CW_AND_ADMIN_MANAGEMENT_ROLE_DB" )
@AllArgsConstructor
@NoArgsConstructor
public class EntityForRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8733727203290789618L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private Integer roleid;
	@Column(name = "ROLE_NAME",length = 20)
	private String role;
}
