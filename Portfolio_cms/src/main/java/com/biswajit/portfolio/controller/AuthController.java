package com.biswajit.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biswajit.portfolio.dto.LoginRequestDto;
import com.biswajit.portfolio.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	int count=0;
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDto dto)
	{
		System.out.println("AuthController count"+ count++);
      return	authService.login(dto);
      	
      //	return "Login Sucessful";
	}

}
