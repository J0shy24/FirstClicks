package com.project.firstclicks.repository.course;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entityIDs.CourseID;

public interface CourseRepository extends JpaRepository<Course, CourseID>{
	Optional<Course> findByName(String name);
	
	List<Course> findTop6ByOrderByCreatedAt();
}
