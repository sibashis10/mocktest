package com.tms.mocks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tms.mocks.domain.Role;
import com.tms.mocks.repository.RoleRepository;

@Service
public class RoleService {
	
	private final RoleRepository repository;

    public RoleService(final RoleRepository repository) {
        this.repository = repository;
    }

    public Optional<Role> findById(final Long id) {
        return repository.findById(id);
    }
    
    public void save(final Role role) {
    	repository.save(role);
    }

    public List<Role> findAll() {
        return repository.findAll();
    }
    
    public void delete(final Long id) {
        repository.deleteById(id);
    }

}
