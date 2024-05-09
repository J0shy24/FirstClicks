package com.project.firstclicks.controller.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequestDTO {
	@NotEmpty(message = "El usuario es obligatorio")
	@NotBlank(message = "El usuario es obligatorio")
	private String userName;
	@NotEmpty(message = "La contraseña es obligatoria")
	@NotBlank(message = "La contraseña es obligatoria")
	@Size(min = 8,message = "La contraseña debe ser al menos 8 caracteres")
	private String password;
}
