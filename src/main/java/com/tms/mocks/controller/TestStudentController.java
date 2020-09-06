package com.tms.mocks.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tms.mocks.domain.TestStudent;
import com.tms.mocks.service.TestStudentService;
import com.tms.mocks.service.vo.AnswerVO;

@RestController
public class TestStudentController {
	
	private final TestStudentService service;
	
	public TestStudentController(final TestStudentService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/teststudent/{testId}/{userId}")
	public ResponseEntity<Object> getAllTestStudents(final @PathVariable Long testId, final @PathVariable Long userId) {
		return ResponseEntity.status(HttpStatus.OK).body(service.getTestUserWiseTestStudent(testId, userId));
	}
	
	@GetMapping(value = "/teststudent/{id}")
	public ResponseEntity<Object> getTestStudent(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	@PostMapping(value = "/teststudent")
	public ResponseEntity<Object> saveTestStudent(final @RequestBody TestStudent testStudent) {
		service.save(testStudent);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "/teststudent/{id}")
	public ResponseEntity<Object> updateTestStudent(final @PathVariable Long id, final @RequestBody TestStudent testStudent) {
		service.update(id, testStudent);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping(value = "/teststudent/{id}")
	public ResponseEntity<Object> deleteTestStudent(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping(value = "/submittest")
	public ResponseEntity<Object> submitTest(final @RequestBody List<AnswerVO> answerVOs) {
		service.submitAnswers(answerVOs);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
