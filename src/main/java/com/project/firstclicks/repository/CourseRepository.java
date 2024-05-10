package com.project.firstclicks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.firstclicks.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Integer>{
	

}
