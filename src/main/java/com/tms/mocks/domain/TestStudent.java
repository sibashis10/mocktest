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
@Table(name="TESTS_STUDENTS")
public class TestStudent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(initialValue = 1, allocationSize = 1, name="TESTS_STUDENTS_ID_GENERATOR", sequenceName="TESTS_STUDENTS_ID_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TESTS_STUDENTS_ID_GENERATOR")
	private Long id;

	@Column(name="test_id")
	private Long testId;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="exam_end_successfully")
	private Boolean examEndSuccessfully;
	
	@Column(name="marks_obtained")
	private Integer marksObtained;
	
	@Column(name="negative_marking")
	private Integer negativeMarking;

	@Column(name="exam_start_time")
	private Timestamp examStartTime;
	
	@Column(name="exam_end_time")
	private Timestamp examEndTime;

}
