package com.project.Firstclicksfinal.model.dto;

import com.project.Firstclicksfinal.model.entity.User;

import lombok.Data;

@Data
public class UserProfileDTO {
	private Integer Id;
	private String firstName;
	private String lastName;
	private String email;
	private User.Role role;
}
