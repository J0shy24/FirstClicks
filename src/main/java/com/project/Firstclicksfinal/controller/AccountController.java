package com.project.Firstclicksfinal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.Firstclicksfinal.model.dto.SignupFormDTO;
import com.project.Firstclicksfinal.model.dto.UserProfileDTO;
import com.project.Firstclicksfinal.service.AccountService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	private AccountService accountService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/signup")
	public UserProfileDTO signup(@RequestBody @Validated SignupFormDTO signformDTO) {
		return accountService.singnup(signformDTO);
	}
}
