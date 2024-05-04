package com.project.firstclicks.controller.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequestDTO {
	@NotEmpty(message = "Username is mandatory")
	@NotBlank(message = "Username is mandatory")
	private String userName;
	@NotEmpty(message = "Password is mandatory")
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8,message = "password should atleast be 8 characters long.")
	private String password;
}
