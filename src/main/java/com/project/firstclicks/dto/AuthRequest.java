package com.project.firstclicks.dto;

import lombok.Data;

@Data
public class AuthRequest {
	private String email;
	private String password;
}
