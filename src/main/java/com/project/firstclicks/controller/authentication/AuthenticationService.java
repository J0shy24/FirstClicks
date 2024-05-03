package com.project.firstclicks.controller.authentication;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.firstclicks.repository.user.RoleRepository;
import com.project.firstclicks.repository.user.StudentRepository;
import com.project.firstclicks.repository.user.TutorRepository;

import jakarta.mail.MessagingException;

import com.project.firstclicks.repository.user.TokenRepository;

import lombok.RequiredArgsConstructor;

import com.project.firstclicks.dto.RequestUserClientDTO;
import com.project.firstclicks.email.EmailService;
import com.project.firstclicks.email.EmailTemplateName;
import com.project.firstclicks.entity.Client;
import com.project.firstclicks.entity.Student;
import com.project.firstclicks.entity.Token;
import com.project.firstclicks.entity.Tutor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final TutorRepository tRepository;
	private final StudentRepository sRepository;
	private final TokenRepository tokenRepository;
	private final EmailService emailService;
	
	@Value("${application.mailing.frontend.activation-url}")
	private String activationURL;
	
	
	//Registrar Cliente
	public void register(RequestUserClientDTO request) throws MessagingException {
		
		var userRole = roleRepository.findById(request.getRoleId())
				.orElseThrow(()-> new IllegalStateException("Role is not initialized"));
		
			//TODO Mejora la funcion, que hay demasiado codigo duplicado.
		
			//Si es tutor
			if(userRole.getRoleName().equals("TUTOR")) {
				Tutor newTutor = new Tutor();
				
				newTutor.setUsername(request.getUserName());
				newTutor.setPassword(passwordEncoder.encode(request.getPassword()));
				newTutor.setFirstName(request.getFirstName());
				newTutor.setLastName(request.getLastName());
				newTutor.setEmail(request.getEmail());
				newTutor.setDateOfBirth(request.getDateOfBirth());
				newTutor.setGender(request.getGender());
				newTutor.setAddress(request.getAddress());
				newTutor.setDescription(request.getDescription());
				newTutor.setRoles(List.of(userRole));
				newTutor.setAccountLocked(false);
				//User no esta habilitado porque tendrá que activar su cuenta
				newTutor.setEnabled(false);
				
				if(request.getPhotoRoute().isEmpty()||request.getPhotoRoute().isBlank()||request.getPhotoRoute()==null) {
					newTutor.setPhotoRoute("https://picsum.photos/200");
				}else {
					newTutor.setPhotoRoute(request.getPhotoRoute());
				}
				
				
				tRepository.saveAndFlush(newTutor);
				sendEmailValidation(newTutor);
			}
			
			//Si es Estudiante
			if(userRole.getRoleName().equals("STUDENT")) {
				Student newStudent = new Student();
				
				newStudent.setUsername(request.getUserName());
				newStudent.setPassword(passwordEncoder.encode(request.getPassword()));
				newStudent.setFirstName(request.getFirstName());
				newStudent.setLastName(request.getLastName());
				newStudent.setEmail(request.getEmail());
				newStudent.setDateOfBirth(request.getDateOfBirth());
				newStudent.setGender(request.getGender());
				newStudent.setAddress(request.getAddress());
				newStudent.setDescription(request.getDescription());
				newStudent.setRoles(List.of(userRole));
				newStudent.setAccountLocked(false);
				//User no esta habilitado porque tendrá que activar su cuenta
				newStudent.setEnabled(false);
				
				if(request.getPhotoRoute().isEmpty()||request.getPhotoRoute().isBlank()||request.getPhotoRoute()==null) {
					newStudent.setPhotoRoute("https://picsum.photos/200");
				}else {
					newStudent.setPhotoRoute(request.getPhotoRoute());
				}
				
				sRepository.saveAndFlush(newStudent);
				sendEmailValidation(newStudent);
			}
		
	}
	
	//Envia el email de activación de cuenta.
	private void sendEmailValidation(Client client) throws MessagingException {
		var newToken = generateAndSaveActivationToken(client);
		emailService.sendEmail(client.getEmail(), client.getFullName(), EmailTemplateName.ACTIVATE_ACCOUNT,activationURL, newToken, "Account Activation");
	}

	private String generateAndSaveActivationToken(Client client) {
		//generar un token
		String generatedToken = generateActivationToken(6);
		Token token = new Token();
				
		token.setToken(generatedToken);
		token.setCreatedAt(LocalDateTime.now());
		//caduca en 15 mins
		token.setExpiresAt(LocalDateTime.now().plusMinutes(15));
		token.setUser(client);
		
		tokenRepository.saveAndFlush(token);
		return generatedToken;
	}

	private String generateActivationToken(int length) {
		String characters = "0123456789";
		StringBuilder tokenBuilder = new StringBuilder();
		SecureRandom secureRandom = new SecureRandom();
		
		for(int i=0;i<length;i++) {
			int randomIndex = secureRandom.nextInt(characters.length());
			tokenBuilder.append(characters.charAt(randomIndex));
		}
		return tokenBuilder.toString();
	}

}