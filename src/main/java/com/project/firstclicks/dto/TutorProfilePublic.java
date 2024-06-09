package com.project.firstclicks.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TutorProfilePublic {
	Integer id;
	String firstName;
	String lastName;
	String photoRoute;
	String description;
	Integer phoneNumber;
}
