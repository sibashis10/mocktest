package com.tms.mocks.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "CLASSES")
public class Class {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String className;
	private String classCode;
	private Long createdBy;
	@CreationTimestamp
	private Date createdOn;
}