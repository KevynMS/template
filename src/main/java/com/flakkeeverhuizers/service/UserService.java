package com.flakkeeverhuizers.service;

import com.flakkeeverhuizers.controller.model.request.UserRequest;
import com.flakkeeverhuizers.db.entity.User;
import com.flakkeeverhuizers.db.repository.*;
import com.flakkeeverhuizers.exception.AuthenticationException;
import com.flakkeeverhuizers.exception.SignInException;
import com.flakkeeverhuizers.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service(value = "userService")
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bcryptEncoder;

	public void save(UserRequest userRequest) {
		if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
			throw new SignInException("Email already in use");
		}

		User user = new User();
		user.setFirstName(userRequest.getFirstName());
		user.setLastName(userRequest.getLastName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(bcryptEncoder.encode(userRequest.getPassword()));
		user.setCreatedAt(LocalDateTime.now());
		user.setSuperAdmin(Boolean.TRUE);

		userRepository.save(user);
	}

	public User findByID(String userID) {
		return userRepository.findById(UUID.fromString(userID))
				.orElseThrow(() -> new UserNotFoundException("User not found for id: " + userID));
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found for email: " + email));
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new AuthenticationException("Email and/or password invalid."));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		if (user.getSuperAdmin()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return authorities;
	}
}