package com.project.Firstclicksfinal.model.dto;




import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupFormDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    
    @NotBlank
    private String userName;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 4)
    private String password;
    
    
	private String photoRoute;
    
    
    @Enumerated(EnumType.STRING)
    private Role role;
    //private com.project.Firstclicksfinal.entity.User.Role role;
    
    public enum Role {
        STUDENT,
        TUTOR
    }
    
}
