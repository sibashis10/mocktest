package com.tms.mocks.controller;

import com.tms.mocks.domain.Class;
import com.tms.mocks.service.ClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClassController {
	private final ClassService classService;

	public ClassController(final ClassService classService) {
		this.classService = classService;
	}

	@GetMapping(value = "/classes")
	public ResponseEntity<Object> getAllClasses() {
		return ResponseEntity.status(HttpStatus.OK).body(classService.getAllClasses());
	}

	@GetMapping(value = "/classes/{classCode}")
	public ResponseEntity<Object> getAllClass(final @PathVariable String classCode) {
		return ResponseEntity.status(HttpStatus.OK).body(classService.getClassByCode(classCode));
	}

	@PostMapping(value = "/classes")
	public ResponseEntity<Object> getAllClass(final @RequestBody Class clazz) {
		classService.saveClass(clazz);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
