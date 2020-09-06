package com.tms.mocks.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tms.mocks.domain.Role;
import com.tms.mocks.service.RoleService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/")
@Slf4j
public class RoleController {

	private final RoleService service;

	public RoleController(final RoleService service) {
		this.service = service;
	}

	@GetMapping(value = "/roles")
	public ResponseEntity<Object> getAllRoles() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@GetMapping(value = "/roles/{id}")
	public ResponseEntity<Object> getRole(final @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
	}

	@PostMapping(value = "/roles")
	public ResponseEntity<Object> saveRole(final @RequestBody Role role) {
		log.debug(role.toString());
		service.save(role);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(value = "/roles/{id}")
	public ResponseEntity<Object> updateRole(final @PathVariable Long id, final @RequestBody Role updateRole) {
		Optional<Role> role = service.findById(id);

		role.ifPresent(object -> {
			Role.builder().name(updateRole.getName()).description(updateRole.getDescription()).build();
			service.save(object);
		});

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@DeleteMapping(value = "/roles/{id}")
	public ResponseEntity<Object> deleteRole(final @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
