package com.project.firstclicks.entity;


import java.io.Serializable;
import java.util.Date;

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
public abstract class User implements Serializable{
	
	private static final long serialVersionUID = 7495946852773018475L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(
		length = 50,
		unique = true,
		nullable = false
			)
	private String userName;
	private String password;
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastSession;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	
}
