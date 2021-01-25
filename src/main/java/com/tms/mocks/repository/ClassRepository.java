package com.tms.mocks.repository;

import com.tms.mocks.domain.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Clazz, Long> {
	Clazz findByClassCode(final String code);
}