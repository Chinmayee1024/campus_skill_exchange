package com.chinmayee.campusskill.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegisterRequestDto {
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String name;
	@NotBlank(message = "Email is required")
	@Email(message = "please provide a valid email")
	private String email;
	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 100, message = "Password must contain at least 8 characters")
	private String password;
	@Size(max = 100, message = "College name cannot exceed 100 characters")
	private String college;
	
}
