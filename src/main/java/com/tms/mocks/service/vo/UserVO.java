package com.tms.mocks.service.vo;

import java.util.Set;

import com.tms.mocks.domain.Student;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {
	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private Boolean active;
	private Boolean approvedByAdmin;
	private Student student;
	private Set<Long> roles; 
}
