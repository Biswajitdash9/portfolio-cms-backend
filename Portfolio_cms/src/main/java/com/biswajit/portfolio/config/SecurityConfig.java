package com.biswajit.portfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.biswajit.portfolio.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter)
	{
		this.jwtAuthenticationFilter=jwtAuthenticationFilter;
	}
	@Bean
	public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception
	{
		http
		.cors(cors->{})
		.csrf(csrf->csrf.disable())
		.sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    )
		    .authorizeHttpRequests(auth->auth
		    		.requestMatchers("/api/about/add").hasRole("ADMIN")
		    		.requestMatchers("/api/auth/**").permitAll()
		    		.anyRequest().authenticated()).
		    		
		    		addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		    		;
		
		return http.build();
	
	}
	
	
	@Bean
	public AuthenticationManager authenticationManager(
			               AuthenticationConfiguration configuration)
	{
		return configuration.getAuthenticationManager();
	}
	
	
	public CorsConfigurationSource corsConfigurationSource()
	{
		CorsConfiguration configuration=new CorsConfiguration();
		
		configuration.addAllowedOrigin("http://localhost:5173");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		
		org.springframework.web.cors.UrlBasedCorsConfigurationSource source =
				         new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
