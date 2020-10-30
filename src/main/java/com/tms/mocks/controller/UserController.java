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

import com.tms.mocks.service.UserService;
import com.tms.mocks.service.vo.UserVO;

@RestController
public class UserController {
	
	private final UserService service;
	
	public UserController(final UserService service) {
		this.service = service;
	}
	
	@GetMapping(value = "/users")
	public ResponseEntity<Object> getAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@GetMapping(value = "/users/{id}")
	public ResponseEntity<Object> getUser(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}

	@PostMapping(value = "/users")
	public ResponseEntity<Object> saveUser(final @RequestBody UserVO userVO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userVO));
	}

	@PutMapping(value = "/users/{id}")
	public ResponseEntity<Object> updateUser(final @PathVariable Long id, final @RequestBody UserVO userVO) {
		return ResponseEntity.status(HttpStatus.OK).body(service.update(id, userVO));
	}
	
	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<Object> deleteUser(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
