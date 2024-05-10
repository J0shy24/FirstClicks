package com.project.firstclicks.controller.course;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.firstclicks.entity.Course;
import com.project.firstclicks.service.CourseService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("courses")
@RestController
public class CourseController {
	private CourseService courseService;
	
	@GetMapping("/last")
	public List<Course> getLast() {
		return courseService.findLast6Courses();
	}
	
	@GetMapping
	public Page<Course> paginate(@PageableDefault(sort = "name", size = 5) Pageable pageable) {
		return courseService.paginate(pageable);
	}
	
	@GetMapping("/{name}")
	public Course get(@PathVariable String name) {
		return courseService.findByName(name);
	}
}
