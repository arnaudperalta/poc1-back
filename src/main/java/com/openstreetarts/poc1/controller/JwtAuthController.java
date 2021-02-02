package com.openstreetarts.poc1.controller;

import com.openstreetarts.poc1.dto.AuthDTO;
import com.openstreetarts.poc1.dto.JwtDTO;
import com.openstreetarts.poc1.dto.UserDTO;
import com.openstreetarts.poc1.model.UserEntity;
import com.openstreetarts.poc1.repository.UserRepository;
import com.openstreetarts.poc1.service.JwtService;
import com.openstreetarts.poc1.service.UserService;
import com.openstreetarts.poc1.util.ApiRestController;
import com.openstreetarts.poc1.util.JwtUtil;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@ApiRestController
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

	@Autowired
	private UserService userService;

	@PostMapping(value = "/register")
	public UserEntity postRegister(@RequestBody UserDTO user) throws Exception {
		return userService.register(user);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<?> postAuthenticate(@RequestBody AuthDTO request) throws Exception {
		// Tous le code ci dessous devrait être dans un seul Service
		// Comme la méthode du dessus
		// De plus, avec la gestion automatique des exceptions, vous pouvez utiliser autre chose que ResponseEntity<?>
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		final UserDetails userDetails = jwtService.loadUserByUsername(request.getEmail());
		final String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtDTO(token));
	}

	@PostMapping(value = "/token_test")
	public ResponseEntity<?> postTokenTest() {
		// Pareil, ResponseEntity peut être remplacer par un DTO pour toujours avoir du JSON en retour
		// --> traitement unifier côté front !
		return ResponseEntity.ok("Token valide.");
	}
}
