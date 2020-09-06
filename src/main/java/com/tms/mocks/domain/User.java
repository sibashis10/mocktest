package com.tms.mocks.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Data;


/**
 * The persistent class for the users database table.
 * 
 */
@Data
@Builder
@Entity
@Table(name="USERS")
//@NamedQuery(name="User.findAll", query="SELECT t FROM User t")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERS_ID_GENERATOR", sequenceName="USERS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_ID_GENERATOR")
	@Column(unique=true, nullable=false)
	private Long id;
	
	@Column(name="first_name", nullable=false, length=50)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=50)
	private String lastName;
	
	@Column(name="user_name", unique=true, nullable=false, length=255)
	private String userName;
	
	@Column(name="password", nullable=false, length=64)
	private String password;
	
	@Column(name="active", columnDefinition = "boolean default false")
	private boolean active;
	
	@Column(name="approved_by_admin", columnDefinition = "boolean default false")
	private boolean approvedByAdmin;
	
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
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "USERS_ROLES",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;
	
}