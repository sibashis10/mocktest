package com.tms.mocks.repository;

import com.tms.mocks.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
	Class findByClassCode(final String code);
}