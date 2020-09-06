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

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "CHAPTERS")
public class Chapter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "CHAPTERS_ID_GENERATOR", sequenceName = "CHAPTERS")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAPTERS_ID_GENERATOR")
	private Long id;

	private String name;

	@Column(name = "class_id")
	private Long classId;

	@Column(name = "subject_id")
	private Long subjectId;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "modified_by")
	private Long modifiedBy;

	@Column(name = "modified_date")
	private Timestamp modifiedDate;

}
