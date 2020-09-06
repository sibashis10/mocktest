package com.tms.mocks.service.vo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestVO {
	private Long id;
	private String name;
	private Long classId;
	private Long subjectId;
	private String description;
	private String testType;
	private Timestamp validFrom;
	private Timestamp validTo;
	private Integer noOfQuestions;
	private Time duration;
	private Integer totalMarks;
	private Integer passMarks;
	private Integer noOfAttempt;
	private Boolean negativeMark;
	private Set<Long> questionIds;
}
