package com.project.firstclicks.service.course;


import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.firstclicks.dto.CourseFormDTO;
import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entityIDs.CourseID;
import com.project.firstclicks.exception.ResourceNotFoundException;
import com.project.firstclicks.repository.course.CourseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TutorCourseAdminService {
	private CourseRepository courseRepository;
	private ModelMapper modelMapper;
	
	public List<Course> findAll() {
		return courseRepository.findAll();
	}
	
	public Page<Course> paginate(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}
	
	public Course create(CourseFormDTO courseFormDTO) {
		
		Course course = modelMapper.map(courseFormDTO, Course.class);
		course.setIsActive(true);
		course.setCreatedAt(LocalDateTime.now());
		
		return courseRepository.save(course);
	}
	
	public Course findById(CourseID id) {
		return courseRepository.findById(id)
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	public Course update(CourseID id, CourseFormDTO courseFormDTO) {
		Course courseFromDB = findById(id);
		modelMapper.map(courseFormDTO, courseFromDB);
		
		courseFromDB.setUpdatedAt(LocalDateTime.now());
		
		return courseRepository.save(courseFromDB);
	}
	
	public void delete(CourseID id) {
		Course course = findById(id);
		courseRepository.delete(course);
	}
}
