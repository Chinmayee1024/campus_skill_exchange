package com.chinmayee.campusskill.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chinmayee.campusskill.entity.User;
import com.chinmayee.campusskill.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService { //UserDetailsService is an interface provided by Spring Security.

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email.toLowerCase().trim())
				.orElseThrow(() -> new UsernameNotFoundException("User not found with this mail : " + email));
		
		
		return new org.springframework.security.core.userdetails.User(
			    user.getEmail(),       // email
			    user.getPassword(),    // password 
			    user.getEnabled(),     // whether account is enabled or disabled()
			    true,                  // account has not expired 
			    true,                  // Credentials have not expired
			    true,                  // Account is not locked
			    List.of(               // User's granted authorities 
			        new SimpleGrantedAuthority(
			            "ROLE_" + user.getRole().name()
			        )
			    )
			);
	}

}

//Spring Security need this information
//
//Username and password: To authenticate the user during login.
//
//Authorities: To check whether the user has permission to access a resource.
//
//Account status: To determine whether the user is allowed to authenticate.
