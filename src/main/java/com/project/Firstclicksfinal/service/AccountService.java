package com.project.Firstclicksfinal.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Firstclicksfinal.exception.BadRequestException;
import com.project.Firstclicksfinal.exception.ResourceNotFoundException;
import com.project.Firstclicksfinal.model.dto.SignupFormDTO;
import com.project.Firstclicksfinal.model.dto.UserProfileDTO;
import com.project.Firstclicksfinal.model.entity.User;
import com.project.Firstclicksfinal.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;
	
	public UserProfileDTO singnup(SignupFormDTO signupFormDTO) {
		boolean emailAlreadyExists = userRepository.existsByEmail(signupFormDTO.getEmail());
		
		if (emailAlreadyExists) {
			throw new BadRequestException("el email ya existe");
		}
		
		User user = modelMapper.map(signupFormDTO, User.class);
		user.setPassword(passwordEncoder.encode(signupFormDTO.getPassword()));
		user.setRole(RoleSignToRole(signupFormDTO.getRole()));
		user.setCreatedAt(LocalDateTime.now());
		
		userRepository.save(user);
		
		return modelMapper.map(user, UserProfileDTO.class);
	}
	
	static User.Role RoleSignToRole(SignupFormDTO.Role valor) {
		return User.Role.values()[valor.ordinal()];
	}
	
	public UserProfileDTO findByEmail(String email) {
		User user = userRepository
				.findOneByEmail(email)
				.orElseThrow(ResourceNotFoundException::new);
		
		return modelMapper.map(user, UserProfileDTO.class);
	}
}
