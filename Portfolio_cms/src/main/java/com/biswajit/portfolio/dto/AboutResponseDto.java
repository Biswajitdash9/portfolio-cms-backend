package com.biswajit.portfolio.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AboutResponseDto 
{
	private String title;
	
	private String shortDescription;
	
	private String description;
	
	private MultipartFile profileImage;
	
	private String resumeUrl;
	
	private String location;

}
