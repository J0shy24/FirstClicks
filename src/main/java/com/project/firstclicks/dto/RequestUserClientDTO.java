package com.project.firstclicks.dto;

//DTO para Estudiantes y Tutores
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUserClientDTO {
	
	@NotEmpty(message = "Username is mandatory")
	@NotBlank(message = "Username is mandatory")
	private String userName;
	@NotEmpty(message = "Password is mandatory")
	@NotBlank(message = "Password is mandatory")
	@Size(min = 8,message = "password should atleast be 8 characters long.")
	private String password;
	@NotEmpty(message = "First name is mandatory")
	@NotBlank(message = "First name is mandatory")
	private String firstName;
	@NotEmpty(message = "Last name is mandatory")
	@NotBlank(message = "Last name is mandatory")
	private String lastName;
	private LocalDate dateOfBirth;
	private Integer roleId;
	private String gender;
	@NotEmpty(message = "Email is mandatory")
	@NotBlank(message = "Email is mandatory")
	@Email(message = "Wrong email format")
	private String email;
	@NotEmpty(message = "Address is mandatory")
	@NotBlank(message = "Address is mandatory")
	private String address;
	private String description;
	private String photoRoute;
}
