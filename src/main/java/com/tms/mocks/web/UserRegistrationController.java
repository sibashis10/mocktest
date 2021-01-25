package com.tms.mocks.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tms.mocks.service.UserService;
import com.tms.mocks.service.vo.UserVO;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registrationUserAccount(@ModelAttribute("user") UserVO registrationDTO) {
		userService.save(registrationDTO);
		return "redirect:/registration?success";
	}

}
