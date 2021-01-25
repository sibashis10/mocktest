package com.tms.mocks.controller;

import com.tms.mocks.domain.Subject;
import com.tms.mocks.service.ClassService;
import com.tms.mocks.service.SubjectService;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectController {
	private final ClassService classService;
	private final SubjectService subjectService;

	public SubjectController(final SubjectService subjectService, final ClassService classService) {
		this.classService = classService;
		this.subjectService = subjectService;
	}

	@GetMapping(value = "/subjects")
	public ResponseEntity<Object> getSubjects(final @RequestParam (required = false) String code) {
		if(code != null)
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectByCode(code.toUpperCase()));
		else	
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAllSubjects());
	}

	@GetMapping(value = "/subjects/{id}")
	public ResponseEntity<Object> getSubject(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubject(id));
	}
	
	@GetMapping(value = "classes/{classId}/subjects")
	public ResponseEntity<Object> getSubjectsByClass(final @PathVariable Long classId) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectsByClazz(classId));
	}

	@PostMapping(value = "/subjects")
	public ResponseEntity<Object> saveSubject(final @RequestBody Subject subject) {
		Optional<com.tms.mocks.domain.Clazz> clazz = classService.getClass(subject.getClazz().getId());
		if(clazz.isPresent()) {
			subject.setClazz(clazz.get());
			subjectService.saveSubject(subject);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return new ResponseEntity<>("Class not found", HttpStatus.NOT_FOUND);
		}
	}
}