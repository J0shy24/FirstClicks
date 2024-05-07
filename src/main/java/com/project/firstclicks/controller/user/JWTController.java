package com.project.firstclicks.controller.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.firstclicks.dto.AuthRequest;
import com.project.firstclicks.dto.AuthResponse;
import com.project.firstclicks.dto.UserProfileDTO;
import com.project.firstclicks.entity.AppUser;
import com.project.firstclicks.repository.user.UserRepository;
import com.project.firstclicks.service.security.TokenProvider;
import com.project.firstclicks.service.user.AccountService;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
public class JWTController {
	
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	private TokenProvider tokenProvider;
	private AccountService accountService;
	
	@PostMapping("/token")
	public AuthResponse getAccessToken(@RequestBody AuthRequest authRequest) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken (
					authRequest.getEmail(),
					authRequest.getPassword()
		);
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String accessToken = tokenProvider.createAccessToken(authentication);
		UserProfileDTO userProfileDTO = accountService;
		AppUser user = userRepository
				.findByUsername(authRequest.getUsername())
				.orElseThrow(RuntimeException::new);
		
		
		
		
		return new AuthResponse(accessToken, user);
	}
}
