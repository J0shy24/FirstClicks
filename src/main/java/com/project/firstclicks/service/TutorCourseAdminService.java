package com.project.firstclicks.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.firstclicks.dto.CourseDTO;
import com.project.firstclicks.entity.AppUser;
import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entity.TechStack;
import com.project.firstclicks.exceptionhandler.ResourceNotFoundException;
import com.project.firstclicks.repository.AppUserRepository;
import com.project.firstclicks.repository.CourseRepository;
import com.project.firstclicks.repository.TechStackRepository;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TutorCourseAdminService {
	private CourseRepository courseRepository;
	private TechStackRepository techStackRepository;
	private ModelMapper modelMapper;
	private AppUserRepository userRepository;
	
	
	public Integer findIdByUsername(String userName) {
		AppUser user = userRepository.findByUsername(userName)
				.orElseThrow(ResourceNotFoundException::new);
		
		return user.getId();
	}
	
	public List<Course> findAll(Integer id) {
		return courseRepository.findByTutorId(id);
	}
	
	public Page<Course> paginate(Pageable pageable) {
		return courseRepository.findAll(pageable);
	}
	
	public Course create(CourseDTO courseDTO) {
		TechStack techStackNew = new TechStack();
		Course course = new Course();
		
		course.setName(courseDTO.getName());
		course.setDescription(courseDTO.getDescription());
		course.setCoverPath(courseDTO.getCoverPath());
		course.setLevel(courseDTO.getLevel());
		
		course.setCreatedDate(LocalDateTime.now());
		course.setIsActive(true);
		
		Course SaveCourse = courseRepository.save(course);
		
		List<TechStack> tech_sum = new ArrayList<>();
		for (String techstack : courseDTO.getTechStack()) {
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
	
	public Course update(Integer id, CourseDTO courseFormDTO, Integer tutorId) {
		Course courseFromDb = findById(id,tutorId);
		
		modelMapper.map(courseFormDTO, courseFromDb);
		courseFromDb.setUpdatedDate(LocalDateTime.now());
		
		return courseRepository.save(courseFromDb);
	}
	
	public void delete (Integer id, Integer tutorId) {
		Course course = findById(id, tutorId);
		courseRepository.delete(course);
	}
	
}
