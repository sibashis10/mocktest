package com.tms.mocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.mocks.domain.Answer;
import com.tms.mocks.domain.AnswerPK;

public interface AnswerRepository extends JpaRepository<Answer, AnswerPK> {
	
}
