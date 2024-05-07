package com.project.firstclicks.dto;

import com.project.firstclicks.entity.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {
	private String token;
	private AppUser user;
}
