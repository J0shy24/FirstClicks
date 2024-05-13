package com.project.firstclicks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entity.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer>{
	List<StudentCourse> findAllByStudentId(Integer id);
	
	Optional<StudentCourse> findByCourseIdAndStudentId(Integer courseId, Integer studentId);
	
	boolean existsByCourseIdAndStudentId(Integer courseId, Integer studentId);
}
