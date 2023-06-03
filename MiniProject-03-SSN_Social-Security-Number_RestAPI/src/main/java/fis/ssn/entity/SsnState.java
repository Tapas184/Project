package fis.ssn.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;

@Data
@Table(name = "SSN_STATE_TABLE")
@Entity
public class SsnState {

	@Id
	@SequenceGenerator(name = "ssn_state_seq",sequenceName = "ssn_state_seq_gen",initialValue = 100,allocationSize = 1)
	@GeneratedValue(generator = "ssn_state_seq",strategy = GenerationType.SEQUENCE)
	@NonNull
	private Integer stateId;
	@Column(name = "STATE_NAME")
	private String stateName;
}
