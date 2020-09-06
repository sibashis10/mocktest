package com.tms.mocks.repository;

import com.tms.mocks.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findBySubjectCode(final String code);
}
