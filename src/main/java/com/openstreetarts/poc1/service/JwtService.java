package com.openstreetarts.poc1.service;

import java.util.ArrayList;

import com.openstreetarts.poc1.model.UserDTO;
import com.openstreetarts.poc1.model.UserEntity;
import com.openstreetarts.poc1.repository.UserRepository;
import com.openstreetarts.poc1.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
    private JwtUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByEmail(username);
		if (user != null) {
			return new User(user.getEmail(), user.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
	}

	public UserEntity save(UserDTO user) {
        UserEntity newUser = new UserEntity();
        newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		return userRepo.save(newUser);
	}

	public UserEntity loadUserByToken(String token) {
		String email = jwtTokenUtil.getUsernameFromToken(token.substring(7)); // on enlève "Bearer "
        return userRepo.findByEmail(email);
	}
}