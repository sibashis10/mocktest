package com.tms.mocks.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The primary key class for the answers database table.
 * 
 */
@Data
@AllArgsConstructor
@Embeddable
public class AnswerPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "test_student_id")
	private Long testStudentId;

	@Column(name = "question_id")
	private Long questionId;

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AnswerPK)) {
			return false;
		}
		AnswerPK castOther = (AnswerPK) other;
		return this.testStudentId.equals(castOther.testStudentId) && this.questionId.equals(castOther.questionId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.testStudentId.hashCode();
		hash = hash * prime + this.questionId.hashCode();

		return hash;
	}
}