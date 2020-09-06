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
@Table(name="QUESTIONS")
public class Question implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="QUESTIONS_ID_GENERATOR", sequenceName="QUESTIONS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="QUESTIONS_ID_GENERATOR")
	private Long id;

	@Column(name="chapter_id")
	private Long chapterId;
	
	@Column(name="question_image_path")
	private String questionImagePath;
	
	@Column(name="right_answer")
	private String rightAnswer;
	
	@Column(name="multiple_choice")
	private Boolean multipleChoice;
	
	private Integer marks;

	@Column(name="negative_mark")
	private Integer negativeMark;

	@Column(name="no_of_option")
	private Integer noOfOption;

	@Column(name="created_by")
	private Long createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;
	
	@Column(name="modified_by")
	private Long modifiedBy;

	@Column(name="modified_date")
	private Timestamp modifiedDate;
}
