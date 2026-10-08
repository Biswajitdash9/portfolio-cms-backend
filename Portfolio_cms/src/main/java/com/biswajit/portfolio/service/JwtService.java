package com.biswajit.portfolio.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	
	//Secret key for JWT
	@Value(value="${authetication.secretKey}")
	private  String SECRET_KEY ;
	       
	
	//Expiration time of JWT token after creation
	private final Long expirationTime=1000*60*60l;
	
	//private LocalDateTime time;
	
	//SigningKey creation
	public SecretKey getSigningkey()
	{
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	
	//method for generate token
	public String generateToken(String userName)
	{
		
		return Jwts.builder()
				.subject(userName)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis()+expirationTime))
				.signWith(getSigningkey())
				.compact();
	}
	
	//method for extract user name
	
	public String extractUserName(String token)
	{
		return extractAllClaims(token).getSubject();
	}
	
	private Claims extractAllClaims(String token)
	{
		return Jwts.parser()
				.verifyWith(getSigningkey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
	}
	

	//method used for to validate the token which attached with the new request from browser side
	public boolean isTokenValid(String token,String userName)
	
	{
		String extractedUserName=extractUserName(token);
		return extractedUserName.equals(userName);
		
	}

}
