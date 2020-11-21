package com.openstreetarts.poc1.controller;

import com.openstreetarts.poc1.model.JwtRequest;
import com.openstreetarts.poc1.model.JwtResponse;
import com.openstreetarts.poc1.model.UserDTO;
import com.openstreetarts.poc1.model.UserEntity;
import com.openstreetarts.poc1.repository.UserRepository;
import com.openstreetarts.poc1.service.JwtService;
import com.openstreetarts.poc1.util.JwtUtil;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin
public class JwtAuthController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authManager;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> postRegister(@RequestBody UserDTO user) throws Exception {
		if (userRepo.findByEmail(user.getEmail()) != null)
			return new ResponseEntity<>("User already existing.", HttpStatus.BAD_REQUEST);
		if (user.getPassword().length() < UserEntity.PSW_MIN_LENGTH)
			return new ResponseEntity<>("Password too short.", HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(jwtService.save(user));
	}

	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> postAuthenticate(@RequestBody JwtRequest request) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = jwtService.loadUserByUsername(request.getEmail());
		final String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
}
