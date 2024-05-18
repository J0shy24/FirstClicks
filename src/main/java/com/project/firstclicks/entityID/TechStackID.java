package com.project.firstclicks.entityID;

import com.project.firstclicks.entity.Course;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
public class TechStackID {
	private Course course;
	private String techStack;
}
