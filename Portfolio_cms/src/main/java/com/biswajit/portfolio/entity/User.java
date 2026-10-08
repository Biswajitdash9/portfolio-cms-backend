package com.biswajit.portfolio.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.biswajit.portfolio.base.BaseAudit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseAudit
{
	//User field
	@Id
	@SequenceGenerator(name="gen1",sequenceName = "User_seq",initialValue = 1000,allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO,generator = "gen1")
    private Long userId;
	
	@Column(length=30, nullable=false,unique=true)
	private String username;
	
	@Column(length=40,nullable = false,unique = true)
	private String email;
	
	@Column(length = 300,nullable=false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;
	
	
	
}
