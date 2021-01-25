package com.tms.mocks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tms.mocks.domain.Subject;
import com.tms.mocks.repository.SubjectRepository;

@Service
public class SubjectService {
	private final SubjectRepository subjectRepository;

	public SubjectService(final SubjectRepository subjectRepository) {
		this.subjectRepository = subjectRepository;
	}

	public List<Subject> getAllSubjects() {
		return subjectRepository.findAll();
	}
	
	public List<Subject> getSubjectsByClazz(final Long classId) {
		return subjectRepository.findByClazzId(classId);
	}
	
	public Optional<Subject> getSubject(final Long id) {
		return subjectRepository.findById(id);
	}

	public Subject getSubjectByCode(final String code) {
		return subjectRepository.findBySubjectCode(code);
	}

	public void saveSubject(final Subject subject) {
		subjectRepository.save(subject);
	}
}