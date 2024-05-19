package com.project.Firstclicksfinal.model.dto;

import java.util.List;

import com.project.Firstclicksfinal.model.entity.Course;
import com.project.Firstclicksfinal.model.entity.TechStack;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseFormDTO {
	@NotNull
	@Size(min = 3, max = 250)
	private String title;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String coverPath;
	
	@Enumerated(EnumType.STRING)
	private Course.Level level;
	

	private List<String> techStack;
	
}
