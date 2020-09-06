package com.tms.mocks.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "SUBJECTS")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String subjectName;
	private String subjectCode;
	private Long createdBy;
	@CreationTimestamp
	private Date createdOn;
}