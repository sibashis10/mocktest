package com.tms.mocks.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tms.mocks.domain.User;
import com.tms.mocks.service.vo.UserVO;

public interface UserService extends UserDetailsService {
	
	User save(UserVO userVO);
	User findById(Long id);
	List<User> findAll();
	void delete(Long id);
	void update(Long id, UserVO userVO);

}
