package com.biswajit.portfolio.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.biswajit.portfolio.entity.User;
import com.biswajit.portfolio.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
	
	private final IUserRepository userRepo;
	
	public CustomUserDetailsService(IUserRepository userRepo)
	{
		this.userRepo=userRepo;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user= userRepo.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User Not Found"+username));
		
		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole().name())
				.build();
				
		
	}

}
