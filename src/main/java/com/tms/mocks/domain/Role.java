package com.tms.mocks.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;


/**
 * The persistent class for the roles database table.
 * 
 */
@Data
@Builder
@Entity
@Table(name="ROLES")
//@NamedQuery(name="Role.findAll", query="SELECT t FROM Role t")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name="ROLES_ID_GENERATOR", sequenceName="ROLES_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLES_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(unique=true, nullable=false, length=20)
	@NonNull
	private String name;

	@Column(length=255)
	private String description;
	
	@Column(name="created_by")
	private Long createdBy;

	@CreationTimestamp
	@Column(name="created_date", nullable=false)
	private Timestamp createdDate;

	@Column(name="modified_by")
	private Long modifiedBy;

	@UpdateTimestamp
	@Column(name="modified_date")
	private Timestamp modifiedDate;
}