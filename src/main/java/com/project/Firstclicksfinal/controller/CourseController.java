package com.project.Firstclicksfinal.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.Firstclicksfinal.model.entity.Course;
import com.project.Firstclicksfinal.service.CourseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("api/courses")
@RestController
public class CourseController {
	private CourseService courseService;
	
	@GetMapping("/last")
	public List<Course> getLast() {
		return courseService.findLast6Courses();
	}
	
	@GetMapping
	public Page<Course> paginate(@PageableDefault(sort = "title", size = 5) Pageable pageable) {
		return courseService.paginate(pageable);
	}
	
	@GetMapping("/{title}")
	public Course get(@PathVariable String title) {
		return courseService.findByTittle(title);
	}
}
