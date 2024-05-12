package com.project.firstclicks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.firstclicks.entity.StudentCourse;
import com.project.firstclicks.entityID.StudentCourseId;
@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse,StudentCourseId>{

}
