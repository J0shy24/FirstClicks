package com.project.firstclicks.entity.user.client;


import com.project.firstclicks.entity.user.User;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public class Client extends User{
	private String firstName;
	private String lastName1;
	private String lastName2;
	private String email;
	private String photoRoute;
	private String address;
}
