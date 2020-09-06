package com.tms.mocks.domain;

import java.io.Serializable;
import java.sql.Time;
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

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "TESTS")
public class Test implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="TESTS_ID_GENERATOR", sequenceName="TESTS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TESTS_ID_GENERATOR")
	private Long id;

	private String name;
	
	@Column(name="class_id")
	private Long classId;
	
	@Column(name="subject_id")
	private Long subjectId;

	private String description;
	
	@Column(name="test_type")
	private String testType;
	
	@Column(name="valid_from")
	private Timestamp validFrom;

	@Column(name="valid_to")
	private Timestamp validTo;

	@Column(name="no_of_questions")
	private Integer noOfQuestions;
	
	private Time duration;
	
	@Column(name="total_marks")
	private Integer totalMarks;

	@Column(name="pass_marks")
	private Integer passMarks;

	@Column(name="no_of_attempt")
	private Integer noOfAttempt;
	
	@Column(name="negative_mark")
	private Boolean negativeMark;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="modified_by")
	private Long modifiedBy;

	@Column(name="modified_date")
	private Timestamp modifiedDate;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "TESTS_QUESTIONS",
			joinColumns = @JoinColumn(
					name = "test_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
					name = "question_id", referencedColumnName = "id"))
	private Set<Question> questions;

}
