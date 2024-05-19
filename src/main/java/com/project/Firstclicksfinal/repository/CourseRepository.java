package com.project.Firstclicksfinal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Firstclicksfinal.model.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findTop6ByOrderByCreatedAtDesc();
	
	Optional<Course> findByTitle(String title);
	
	Optional<Course> findByIdAndTutorId(Integer id, Integer tutorId);
	
	List<Course> findByTutorId(Integer id);
}
