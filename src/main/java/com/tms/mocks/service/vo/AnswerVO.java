package com.tms.mocks.service.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerVO {
	private Long testStudentId;
	private Long questionId;
	private String answerGiven;
	private Boolean correct;
}
