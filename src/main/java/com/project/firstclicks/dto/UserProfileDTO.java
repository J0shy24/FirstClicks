package com.project.firstclicks.dto;

import com.project.firstclicks.entity.Client;

import lombok.Data;

@Data
public class UserProfileDTO {
	private String username;
	private Client client;

}
