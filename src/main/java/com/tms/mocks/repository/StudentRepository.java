package com.tms.mocks.repository;

import com.tms.mocks.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findByUserId(final Long userId);
	
}
