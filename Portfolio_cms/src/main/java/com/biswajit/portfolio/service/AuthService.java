package com.biswajit.portfolio.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.biswajit.portfolio.dto.LoginRequestDto;

@Service
public class AuthService {
	
	private final AuthenticationManager authenticationManager;
	
	private  final JwtService jwtService; 
	
	public AuthService(AuthenticationManager authenticationManager,JwtService jwtService) {
		this.authenticationManager=authenticationManager;
		this.jwtService=jwtService;
	}
	
	
	public String login(LoginRequestDto dto)
	{
		authenticationManager.authenticate(
			new	UsernamePasswordAuthenticationToken(
					dto.getUserName(),
					dto.getPassword())
				
				);
		
		return jwtService.generateToken(dto.getUserName());
		
	}

}
