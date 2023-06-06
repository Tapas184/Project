package com.phonebook.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "PHONEBOOK")
public class ContactEntity {
	@Id
	@GeneratedValue(generator = "pnone_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "phone_seq", sequenceName = "phone_seq_gen", initialValue = 1000, allocationSize = 1)
	private Integer cid;
	@NonNull
	@Column(name = "contact_name")
	private String name;
	@NonNull
	@Column(name = "contact_email")
	private String email;
	@NonNull
	@Column(name = "contact_phoneNo")
	private Long phoneNo;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date",updatable = false)
	private Date insertdate;
	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "update_date",insertable = false)
	private Date updatedate;

}
