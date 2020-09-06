package com.tms.mocks.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	
	@PostMapping(value = "/auth/login")
	public String login() {
		return "Welcome sir";
	}
	
	

}
