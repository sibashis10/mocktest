package com.tms.mocks.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tms.mocks.domain.Role;
import com.tms.mocks.domain.Student;
import com.tms.mocks.domain.User;
import com.tms.mocks.exception.UserNotFoundException;
import com.tms.mocks.repository.RoleRepository;
import com.tms.mocks.repository.StudentRepository;
import com.tms.mocks.repository.UserRepository;
import com.tms.mocks.service.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	final private UserRepository repository;
	final private RoleRepository roleRepository;
	final private StudentRepository studentRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(final UserRepository repository, final RoleRepository roleRepository,
			final StudentRepository studentRepository) {
		this.repository = repository;
		this.roleRepository = roleRepository;
		this.studentRepository = studentRepository;
	}

	@Override
	public User save(final UserVO userVO) {

		Set<Role> roles = userVO.getRoles()
				.stream()
				.map(roleId -> roleRepository.findById(roleId).orElse(null))
				.collect(Collectors.toSet());

		User user = User
				.builder()
				.firstName(userVO.getFirstName())
				.lastName(userVO.getLastName())
				.userName(userVO.getUserName())
				.password(passwordEncoder.encode(userVO.getPassword()))
				.roles(roles)
				.build();
		
		if(userVO.getStudent() != null)
			studentRepository.save(userVO.getStudent());

		return repository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = repository.findByUserName(userName);
		if (user == null) {
			throw new UserNotFoundException();
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public User findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public void delete(Long id) {
		User user = findById(id);
		Student student = studentRepository.findByUserId(user.getId());
		if(student != null)
			studentRepository.delete(student);
		
		repository.delete(user);
	}

	@Override
	public void update(Long id, UserVO userVO) {
		User user = findById(id);
		if(userVO.getFirstName() != null)
			user.setFirstName(userVO.getFirstName());
		if(userVO.getLastName() != null)
			user.setLastName(userVO.getLastName());
		if(userVO.getPassword() != null)
			user.setPassword(userVO.getPassword());
		if(userVO.getActive() != null)
			user.setActive(userVO.getActive());
		if(userVO.getApprovedByAdmin() != null)
			user.setApprovedByAdmin(userVO.getApprovedByAdmin());
		
		if(userVO.getRoles() != null) {
			Set<Role> roles = userVO.getRoles().stream().map(roleId -> roleRepository.findById(roleId).orElse(null))
				.collect(Collectors.toSet());
			user.setRoles(roles);
		}
		
		if(userVO.getStudent() != null) {
			Student student = studentRepository.findByUserId(user.getId());
			if(userVO.getStudent().getGuardianName() != null)
				student.setGuardianName(userVO.getStudent().getGuardianName());
			if(userVO.getStudent().getSchoolId() != null)
				student.setSchoolId(userVO.getStudent().getSchoolId());
			if(userVO.getStudent().getClassId() != null)
				student.setClassId(userVO.getStudent().getClassId());
			if(userVO.getStudent().getDob() != null)
				student.setDob(userVO.getStudent().getDob());
			if(userVO.getStudent().getContacts() != null)
				student.setContacts(userVO.getStudent().getContacts());
			if(userVO.getStudent().getAddress() != null)
				student.setAddress(userVO.getStudent().getAddress());
			
			studentRepository.save(student);
		}
		
		repository.save(user);
	}

}
