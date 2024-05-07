package com.project.firstclicks.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CourseFormDTO {
	@NotNull
	@Size(min = 3, max = 250)
	private String name;
	
	@NotBlank
	private String description;
	
	@NotBlank
	private String coverPath;
	
}
