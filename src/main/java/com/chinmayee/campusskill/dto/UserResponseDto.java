package com.chinmayee.campusskill.dto;

import com.chinmayee.campusskill.entity.Role;

import lombok.Builder;
import lombok.Getter;
@Getter
@Builder
public class UserResponseDto {
	private Long id;
	private String name;
	private String email;
	private String college;
	private Role role;

}
