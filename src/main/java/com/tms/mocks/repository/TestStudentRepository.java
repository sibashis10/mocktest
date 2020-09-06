package com.tms.mocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.mocks.domain.TestStudent;

public interface TestStudentRepository extends JpaRepository<TestStudent, Long> {

	List<TestStudent> findByTestIdAndUserId(Long testId, Long userId);

}
