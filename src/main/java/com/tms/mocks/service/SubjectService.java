package com.tms.mocks.service;

import com.tms.mocks.domain.Subject;
import com.tms.mocks.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
	private final SubjectRepository subjectRepository;

	public SubjectService(final SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}

	public Subject getSubjectByCode(final String code) {
		return subjectRepository.findBySubjectCode(code);
	}

	public void saveSubject(final Subject subject) {
		subjectRepository.save(subject);
	}
}