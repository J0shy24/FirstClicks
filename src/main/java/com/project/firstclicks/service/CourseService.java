package com.project.firstclicks.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.firstclicks.dto.CoursePublicDTO;
import com.project.firstclicks.dto.TutorProfilePublic;
import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entity.Tutor;
import com.project.firstclicks.exceptionhandler.ResourceNotFoundException;
import com.project.firstclicks.repository.CourseRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseService {
	private CourseRepository courseRepository;
	private ModelMapper modelMapper;
	
	public List<CoursePublicDTO> findLast6Courses() {
		List<Course> CoursesFromDb = courseRepository.findTop6ByOrderByCreatedDate();
		List<CoursePublicDTO> coursePublicDTOs = new ArrayList<>();
		
		TutorProfilePublic publicTutor = new TutorProfilePublic();
		
		for (Course course : CoursesFromDb) {
			CoursePublicDTO sum = new CoursePublicDTO();
			
			modelMapper.map(course.getTutorId(), publicTutor);
			
			sum.setTutor(publicTutor);

			modelMapper.map(course, sum);
			coursePublicDTOs.add(sum);
		}
		
		
		
		return coursePublicDTOs;
	}
	
	public List<CoursePublicDTO> findByName(String name) {
		List<Course> coursesFromDB = courseRepository.findByNameContaining(name);
		
		TutorProfilePublic publicTutor = new TutorProfilePublic();
		List<CoursePublicDTO> coursesPublicDTOs = new ArrayList<>();
		
		if (!coursesFromDB.isEmpty()) {
			for (Course course : coursesFromDB) {
				CoursePublicDTO sum = new CoursePublicDTO();
				
				modelMapper.map(course.getTutorId(), publicTutor);
				
				sum.setTutor(publicTutor);

				modelMapper.map(course, sum);
				coursesPublicDTOs.add(sum);
			}
		}
		
		
		return coursesPublicDTOs;
	}
	
	public Page<CoursePublicDTO> paginate(Pageable pageable) {
		List<Course> CoursesFromDb = courseRepository.findAll();
		List<CoursePublicDTO> coursePublicDTOs = new ArrayList<>();
		
		TutorProfilePublic publicTutor = new TutorProfilePublic();
		
		for (Course course : CoursesFromDb) {
			CoursePublicDTO sum = new CoursePublicDTO();
			
			modelMapper.map(course.getTutorId(), publicTutor);
			
			sum.setTutor(publicTutor);

			modelMapper.map(course, sum);
			coursePublicDTOs.add(sum);
		}
		
		Page<CoursePublicDTO> pageCoursePublicDTO = new PageImpl<>(coursePublicDTOs, pageable, 0);
		
		return  pageCoursePublicDTO;
	}

	public CoursePublicDTO findById(Integer courseId) {
		Course courseFromDb = courseRepository.findById(courseId)
				.orElseThrow(ResourceNotFoundException::new);
		CoursePublicDTO coursePublicDTO = new CoursePublicDTO();
		
		TutorProfilePublic publicTutor = new TutorProfilePublic();
		
		modelMapper.map(courseFromDb.getTutorId(), publicTutor);
		
		coursePublicDTO.setTutor(publicTutor);
		
		modelMapper.map(courseFromDb, coursePublicDTO);
		
		return coursePublicDTO;
	}
}
