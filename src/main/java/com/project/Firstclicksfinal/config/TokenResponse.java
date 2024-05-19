package com.project.Firstclicksfinal.config;

import lombok.Getter;

@Getter
public class TokenResponse {
	private String sub;
	private String role;
	private Integer exp;
}
