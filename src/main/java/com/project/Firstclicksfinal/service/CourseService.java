package com.project.Firstclicksfinal.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.Firstclicksfinal.exception.ResourceNotFoundException;
import com.project.Firstclicksfinal.model.entity.Course;
import com.project.Firstclicksfinal.repository.CourseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseService {
	private CourseRepository courseRepository;
	
	public List<Course> findLast6Courses() {
		return courseRepository.findTop6ByOrderByCreatedAtDesc();
	}
	
	public Course findByTittle(String title) {
		return courseRepository.findByTitle(title)
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	public Page<Course> paginate(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}

}
