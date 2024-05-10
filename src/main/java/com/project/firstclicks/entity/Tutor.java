package com.project.firstclicks.entity;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@Table(name="tutors")
public class Tutor extends Client{
	private static final long serialVersionUID = 4963628534550795467L;
	
	@OneToMany(mappedBy="tutor")
	private List<Course> courses;
}
