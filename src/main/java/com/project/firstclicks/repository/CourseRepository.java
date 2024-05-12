package com.project.firstclicks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.firstclicks.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, CourseID>{
	List<Course> findTop6ByOrderByCreatedDate();
	
	Optional<Course> findByName(String name);
	
	Optional<Course> findByIdAndTutorId(Integer id, Integer tutorId);
	
	List<Course> findByTutorId(Integer id);
		
}
