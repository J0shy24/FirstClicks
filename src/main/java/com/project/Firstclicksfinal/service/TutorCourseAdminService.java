package com.project.Firstclicksfinal.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.Firstclicksfinal.exception.ResourceNotFoundException;
import com.project.Firstclicksfinal.model.dto.CourseFormDTO;
import com.project.Firstclicksfinal.model.entity.Course;
import com.project.Firstclicksfinal.model.entity.TechStack;
import com.project.Firstclicksfinal.model.entity.User;
import com.project.Firstclicksfinal.repository.CourseRepository;
import com.project.Firstclicksfinal.repository.TechStackRepository;
import com.project.Firstclicksfinal.repository.UserRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class TutorCourseAdminService {
	private CourseRepository courseRepository;
	private TechStackRepository techStackRepository;
	private UserRepository userRepository;
	private ModelMapper modelMapper;
	
	public Integer findIdByEmail(String email) {
		User user = userRepository.findOneByEmail(email)
				.orElseThrow(ResourceNotFoundException::new);
		
		return user.getId();
	}
	
	public List<Course> findAll(Integer Id) {
		return courseRepository.findByTutorId(Id);
	}
	
	public Page<Course> paginate(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}
	
	public Course create(CourseFormDTO courseFormDTO, Integer TutorId) {
		TechStack techStackNew = new TechStack();
		
		//Course course = modelMapper.map(courseFormDTO, Course.class);
		Course course = new Course();
		course.setTitle(courseFormDTO.getTitle());
		course.setDescription(courseFormDTO.getDescription());
		course.setCoverPath(courseFormDTO.getCoverPath());
		course.setLevel(courseFormDTO.getLevel());
		course.setCreatedAt(LocalDateTime.now());
		course.setUpdateAt(LocalDateTime.now());
		course.setTutorId(TutorId);
		
		Course SaveCourse = courseRepository.save(course);
		
		List<TechStack> tech_sum = new ArrayList<>();
		for (String techstack : courseFormDTO.getTechStack()) {
			techStackNew.setCourse(SaveCourse);
			techStackNew.setTechStack(techstack);
			tech_sum.add(techStackRepository.save(techStackNew));
		}
		
		SaveCourse.setTechStack(tech_sum);
		
		return SaveCourse;
	}
	
	public Course findById (Integer id, Integer tutorId) {
		return courseRepository.findByIdAndTutorId(id, tutorId)
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	public Course update(Integer id, CourseFormDTO courseFormDTO, Integer tutorId) {
		Course courseFromDb = findById(id,tutorId);
		
		modelMapper.map(courseFormDTO, courseFromDb);
		courseFromDb.setUpdateAt(LocalDateTime.now());
		
		return courseRepository.save(courseFromDb);
	}
	
	public void delete (Integer id, Integer tutorId) {
		Course course = findById(id, tutorId);
		courseRepository.delete(course);
	}
}
