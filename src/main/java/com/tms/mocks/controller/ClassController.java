package com.tms.mocks.controller;

import com.tms.mocks.domain.Clazz;
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
	public ResponseEntity<Object> getClasses(final @RequestParam (required = false) String code) {
		if(code != null)
			return ResponseEntity.status(HttpStatus.OK).body(classService.getClassByCode(code.toUpperCase()));
		else
			return ResponseEntity.status(HttpStatus.OK).body(classService.getAllClasses());
	}

	@GetMapping(value = "/classes/{id}")
	public ResponseEntity<Object> getClass(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(classService.getClass(id));
	}

	@PostMapping(value = "/classes")
	public ResponseEntity<Object> saveClass(final @RequestBody Clazz clazz) {
		classService.saveClass(clazz);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
