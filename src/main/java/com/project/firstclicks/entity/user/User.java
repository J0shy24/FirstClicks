package com.project.firstclicks.entity.user;


import java.time.LocalDateTime;

import com.project.firstclicks.entity.role.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(
		length = 50,
		unique = true
			)
	private String userName;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime lastSession;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
}
