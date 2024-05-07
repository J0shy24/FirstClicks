package com.project.firstclicks.service.user;

import java.time.LocalDateTime;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.firstclicks.dto.SignupFormDTO;
import com.project.firstclicks.dto.UserProfileDTO;
import com.project.firstclicks.entity.AppUser;
import com.project.firstclicks.entity.Client;
import com.project.firstclicks.exception.BadRequestException;
import com.project.firstclicks.repository.user.ClientRepository;
import com.project.firstclicks.repository.user.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountService {
	private UserRepository userRepository;
	private ClientRepository clientRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;
	
	public UserProfileDTO signup(SignupFormDTO signupFormDTO) {
		boolean userNameAlreayExists = userRepository.existsByUsername(signupFormDTO.getUserName());
		boolean emailAlreadyExists = clientRepository.existsByEmail(signupFormDTO.getEmail());
		
		if (emailAlreadyExists) {
			throw new BadRequestException("El email ya está siendo usado");
		}
		
		if (userNameAlreayExists) {
			throw new BadRequestException("El username ya está siendo usado");
		}
		
		AppUser user = new AppUser();
		user.setUsername(signupFormDTO.getUserName());
		user.setPassword(passwordEncoder.encode(signupFormDTO.getPassword()));
		user.setLastSession(LocalDateTime.now());
		userRepository.save(user);
		
		
		Client client = new Client();
		
	}
}






