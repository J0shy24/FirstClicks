package com.project.firstclicks.controller.authentication;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name="Authentication")
public class AuthenticationController {
	
	private final AuthenticationService service;
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.ACCEPTED)
	//@Valid validará el request y generará un mensaje
	public ResponseEntity<?> register(@RequestBody @Valid RequestUserClientDTO request) throws MessagingException{
		service.register(request);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(
			 @RequestBody @Valid AuthenticationRequestDTO request
			){
		AuthenticationResponse access = service.authenticate(request);
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + access.getToken())
				.body(access);
	}
	
//	ResponseEntity
//    .ok()
//    .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
//    .body(authResponse);
	
	}

	@GetMapping("/activate-account")
	public void confirm(@RequestParam String token) throws MessagingException {
		service.activateAccount(token);
	}
	
}
