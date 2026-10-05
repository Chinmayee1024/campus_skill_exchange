package com.chinmayee.campusskill.dto;

import com.chinmayee.campusskill.entity.Role;

public class LoginResponseDto {

	private String token;
	private String tokenType;
	private Long userId;
	private String name;
	private String email;
	private Role role;

}
