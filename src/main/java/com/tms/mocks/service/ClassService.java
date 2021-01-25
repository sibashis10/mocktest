package com.tms.mocks.service;

import com.tms.mocks.domain.Clazz;
import com.tms.mocks.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService {
	private final ClassRepository classRepository;

	public ClassService(final ClassRepository classRepository) {
		this.classRepository = classRepository;
	}

	public List<Clazz> getAllClasses() {
		return classRepository.findAll();
	}

	public Optional<Clazz> getClass(final Long id) {
		return classRepository.findById(id);
	}
	
	public Clazz getClassByCode(final String code) {
		return classRepository.findByClassCode(code);
	}

	public void saveClass(final Clazz clazz) {
		classRepository.save(clazz);
	}
}