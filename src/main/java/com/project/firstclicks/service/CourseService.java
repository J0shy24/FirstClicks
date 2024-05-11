package com.project.firstclicks.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.firstclicks.entity.Course;
import com.project.firstclicks.exceptionhandler.ResourceNotFoundException;
import com.project.firstclicks.repository.CourseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseService {
	private CourseRepository courseRepository;
	
	public List<Course> findLast6Courses() {
		return courseRepository.findTop6ByOrderByCreatedDate();
	}
	
	public Course findByName(String name) {
		return courseRepository.findByName(name)
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	public Page<Course> paginate(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}
}
