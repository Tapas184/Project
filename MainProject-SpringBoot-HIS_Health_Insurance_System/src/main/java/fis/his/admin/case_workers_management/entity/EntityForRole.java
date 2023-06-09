package fis.his.admin.case_workers_management.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "CW_AND_ADMIN_MANAGEMENT_ROLE_DB" )
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE CW_AND_ADMIN_MANAGEMENT_ROLE_DB SET ROLE_STATUS='INACTIVE' WHERE ROLE_ID=?")
public class EntityForRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8733727203290789618L;
	@Id
	@SequenceGenerator(name = "Role_seq",sequenceName = "se1",initialValue = 1,allocationSize = 1)
	@GeneratedValue(generator = "Role_seq",strategy = GenerationType.SEQUENCE)
	@Column(name = "ROLE_ID")
	private Integer roleid;
	@Column(name = "ROLE_NAME",length = 20,unique = true)
	private String role;
	@Column(name = "ROLE_STATUS",length = 20)
	private String roleStatus="ACTIVE";
}
