package com.template.service;

import com.template.controller.model.request.UserRequest;
import com.template.db.entity.User;
import com.template.db.repository.*;
import com.template.exception.AuthenticationException;
import com.template.exception.RecordNotFoundException;
import com.template.exception.SignInException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
		// TODO Please adapt this method to your specific project
		User user = new User();
		user.setName(userRequest.getFirstName());
		user.setSurname(userRequest.getLastName());
		user.setActive(userRequest.getActivated());
		user.setEmail(userRequest.getEmail());
		user.setPassword(bcryptEncoder.encode(userRequest.getPassword()));

		userRepository.save(user);
	}

	public User findByID(String userID) {
		return userRepository.findById(UUID.fromString(userID))
				.orElseThrow(() -> new RecordNotFoundException("User not found for id: " + userID));
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new RecordNotFoundException("User not found for email: " + email));
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new AuthenticationException("Email and/or password invalid."));
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
	}

	private Set<SimpleGrantedAuthority> getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		authorities.add(user.getAuthorityByRole());

		return authorities;
	}
}