package com.chinmayee.campusskill.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chinmayee.campusskill.dto.RegisterRequestDto;
import com.chinmayee.campusskill.dto.UserResponseDto;
import com.chinmayee.campusskill.entity.User;
import com.chinmayee.campusskill.exceptions.EmailAlreadyExistsException;
import com.chinmayee.campusskill.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserResponseDto register(RegisterRequestDto request) {

		// Check whether email already exists
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new EmailAlreadyExistsException("An Account with this email already exists");
		}
		// Create User entity

		User user = User.builder().name(request.getName()).email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword())).college(request.getCollege()).build();
        //Save user
		User savedUser = userRepository.save(user);

		// Convert entity to response DTO
		return UserResponseDto.builder().id(savedUser.getId()).name(savedUser.getName()).email(savedUser.getEmail())
				.college(savedUser.getCollege()).role(savedUser.getRole()).build();

	}

}
