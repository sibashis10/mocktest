package com.tms.mocks.controller;

import com.tms.mocks.domain.Subject;
import com.tms.mocks.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SubjectController {
	private final SubjectService subjectService;

	public SubjectController(final SubjectService subjectService) {
		this.subjectService = subjectService;
	}

	@GetMapping(value = "/subjects")
	public ResponseEntity<Object> getAllSubjects() {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.getAllSubjects());
	}

	@GetMapping(value = "/subjects/{subjectCode}")
	public ResponseEntity<Object> getSubject(final @PathVariable String subjectCode) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.getSubjectByCode(subjectCode));
	}

	@PostMapping(value = "/subjects")
	public ResponseEntity<Object> saveSubject(final @RequestBody Subject subject) {
		subjectService.saveSubject(subject);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}