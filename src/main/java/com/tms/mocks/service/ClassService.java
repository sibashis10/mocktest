package com.tms.mocks.service;

import com.tms.mocks.domain.Class;
import com.tms.mocks.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService {
	private final ClassRepository classRepository;

	public ClassService(final ClassRepository classRepository) {
		this.classRepository = classRepository;
	}

	public List<Class> getAllClasses() {
		return classRepository.findAll();
	}

	public Class getClassByCode(final String code) {
		return classRepository.findByClassCode(code);
	}

	public void saveClass(final Class clazz) {
		classRepository.save(clazz);
	}
}