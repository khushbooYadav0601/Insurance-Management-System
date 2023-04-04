package com.insuranceManagement.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceManagement.configuration.CustomUserDetailsService;
import com.insuranceManagement.configuration.JwtUtil;
import com.insuranceManagement.models.LoginRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class PublicController {
	
	private AuthenticationManager authenticationManager;
	
	private JwtUtil jwtTokenUtil;
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					request.getEmail(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password", e);
		}
		final UserDetails user = customUserDetailsService.loadUserByUsername(request.getEmail());
		final String jwt = jwtTokenUtil.generateToken(user);
		return jwt;
	}
}
