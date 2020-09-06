package com.tms.mocks.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "STUDENTS")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private String guardianName;
	private Long schoolId;
	private Long classId;
	private Date dob;
	private String contacts;
	private String address;
	private Long createdBy;
	@CreationTimestamp
	private Date createdOn;
	private Long modifiedBy;
	@UpdateTimestamp
	private Date modifiedOn;
}