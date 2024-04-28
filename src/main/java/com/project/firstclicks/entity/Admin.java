package com.project.firstclicks.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name="admins")
public class Admin extends AppUser{
	
	private static final long serialVersionUID = -372916635918377201L;

}
