package com.project.firstclicks.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entity.TechStack;

import lombok.Data;

@Data
public class CoursePublicDTO {
	private Integer id;
	private String name;
	private String description;
	private String coverPath;
	private List<TechStack> techStack;
	private Course.Level level;
	private TutorProfilePublic tutor;
	private LocalDateTime createdDate;
}
