package com.tms.mocks.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.mocks.domain.Test;
import com.tms.mocks.service.TestService;
import com.tms.mocks.service.vo.TestVO;

@RestController
public class TestController {
	
	private final TestService service;
	
	public TestController(final TestService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/tests/{classId}/{subjectId}")
	public ResponseEntity<Object> getAllTests(final @PathVariable Long classId, final @PathVariable Long subjectId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getClassSubjectWiseTest(classId, subjectId));
	}
	
	// Get Test entity with all questions
	@GetMapping(value = "/tests/{id}")
	public ResponseEntity<Object> getTest(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@PostMapping(value = "/tests")
	public ResponseEntity<Object> saveTest(final @RequestBody TestVO testVO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(testVO));
	}

	@PutMapping(value = "/tests/{id}")
	public ResponseEntity<Object> updateTest(final @PathVariable Long id, final @RequestBody TestVO testVO) {
		Test test = service.update(id, testVO);
		if(test != null) {
			return ResponseEntity.status(HttpStatus.OK).body(test);
		} else {
			return new ResponseEntity<>("Test is not found", HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping(value = "/tests/{id}")
	public ResponseEntity<Object> deleteTest(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
