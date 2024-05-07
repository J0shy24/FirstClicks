package com.project.firstclicks.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.project.firstclicks.repository.user.RoleRepository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="users")
public class AppUser implements Serializable{
	
	private static final long serialVersionUID = 7495946852773018475L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false,unique=true)
	private String username;
	@Column(nullable=false)
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime lastSession;
	
	  
	public enum Role {
	        ADMIN,
	        USER,
	        TUTOR
	    }
}
