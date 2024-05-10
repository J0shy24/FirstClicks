package com.project.firstclicks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.firstclicks.entity.StudentCourse;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,Integer>{

}
