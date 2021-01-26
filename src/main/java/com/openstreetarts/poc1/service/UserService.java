package com.openstreetarts.poc1.service;

import com.openstreetarts.poc1.dto.UserDTO;
import com.openstreetarts.poc1.exceptions.OSA400Exception;
import com.openstreetarts.poc1.exceptions.OSA409Exception;
import com.openstreetarts.poc1.model.UserEntity;
import com.openstreetarts.poc1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// NOTE : il est possible de faire une interface UserService et donc un UserServiceImpl
// A vous de choisir
@Service
public class UserService  {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;

	public UserEntity register(UserDTO dto) throws OSA409Exception, OSA400Exception {
		Optional<UserEntity> optionalUser = userRepository.findByEmail(dto.getEmail());
		if (optionalUser.isEmpty())
			throw new OSA409Exception("User already existing");

		UserEntity user = optionalUser.get();
		if (user.getPassword().length() < UserEntity.PSW_MIN_LENGTH)
			throw new OSA400Exception("Password too short");
		return jwtService.save(dto);
	}

}