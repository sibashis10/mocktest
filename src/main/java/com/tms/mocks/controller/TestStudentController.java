package com.tms.mocks.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.tms.mocks.domain.TestStudent;
import com.tms.mocks.service.TestStudentService;
import com.tms.mocks.service.vo.AnswerVO;

@RestController
public class TestStudentController {
	
	private final TestStudentService service;
	
	public TestStudentController(final TestStudentService service) {
		this.service = service;
	}
	
	// Get all valid Test entity for a Student
	@GetMapping(value = "/testStudent/{userId}")
	public ResponseEntity<Object> getAllTestStudents(final @PathVariable Long userId) {
		List<TestStudent> testStudents = service.getUserAndExamStatusWiseTestStudent(userId, false);
		List<Test> tests = service.getValidTestList(testStudents);
		return ResponseEntity.status(HttpStatus.OK).body(tests);
	}
	
	// Get TestStudent details for a specific id
	@GetMapping(value = "/testStudent/{id}")
	public ResponseEntity<Object> getTestStudent(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}
	
	// Save Test Student Entity
	@PostMapping(value = "/testStudent")
	public ResponseEntity<Object> saveTestStudent(final @RequestBody TestStudent testStudent) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(testStudent));
	}

	@PutMapping(value = "/testStudent/{id}")
	public ResponseEntity<Object> updateTestStudent(final @PathVariable Long id, final @RequestBody TestStudent testStudent) {
		TestStudent ts = service.update(id, testStudent);
		if(ts != null) {
			return ResponseEntity.status(HttpStatus.OK).body(ts);
		} else {
			return new ResponseEntity<>("Test for the Student is not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/testStudent/{id}")
	public ResponseEntity<Object> deleteTestStudent(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Submit examination for a student
	@PostMapping(value = "/submitTest")
	public ResponseEntity<Object> submitTest(final @RequestBody List<AnswerVO> answerVOs) {
		service.submitAnswers(answerVOs);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	// Assign a Test for List of Students
	@PostMapping(value = "/assignTestStudents/{testId}")
	public ResponseEntity<Object> assignTestStudents(final @PathVariable Long testId, final @RequestBody List<Long> userIds) {
		List<TestStudent> testStudents = userIds.stream()
			.map(userId ->
				TestStudent.builder().testId(testId).userId(userId).build())
			.collect(Collectors.toList());
		
		service.saveAll(testStudents);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(value = "/showHistoryTestList/{userId}")
	public ResponseEntity<Object> getHistoryTestList(@PathVariable Long userId) {
		List<TestStudent> testStudents = service.getUserAndExamStatusWiseTestStudent(userId, true);
		List<Test> tests = service.getHistoryTestList(testStudents);
		return ResponseEntity.status(HttpStatus.OK).body(tests);
	}

}
