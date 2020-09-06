package com.tms.mocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.mocks.domain.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
	
	List<Test> findByClassIdAndSubjectId(final Long classId, final Long subjectId);

}
