package com.project.firstclicks.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.firstclicks.dto.CourseDTO;
import com.project.firstclicks.dto.CoursePublicDTO;
import com.project.firstclicks.dto.TutorProfilePublic;
import com.project.firstclicks.entity.AppUser;
import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entity.TechStack;
import com.project.firstclicks.entity.Tutor;
import com.project.firstclicks.entityID.TechStackID;
import com.project.firstclicks.exceptionhandler.ResourceNotFoundException;
import com.project.firstclicks.repository.AppUserRepository;
import com.project.firstclicks.repository.CourseRepository;
import com.project.firstclicks.repository.TechStackRepository;
import com.project.firstclicks.repository.TutorRepository;

import io.swagger.v3.oas.models.security.SecurityScheme.In;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TutorCourseAdminService {
	private CourseRepository courseRepository;
	private TechStackRepository techStackRepository;
	private ModelMapper modelMapper;
	private AppUserRepository userRepository;
	private TutorRepository tutorRepository;
	
	private Tutor GetTutorByTutorId (Integer tutorId) {
		return tutorRepository.findById(tutorId)
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	public Integer findIdByUsername(String userName) {
		AppUser user = userRepository.findByUsername(userName)
				.orElseThrow(ResourceNotFoundException::new);
		
		return user.getId();
	}
	
	public List<CoursePublicDTO> findAll(Integer id) {
		List<Course> coursesFromDb = courseRepository.findByTutorId(GetTutorByTutorId(id));
		List<CoursePublicDTO> convertPublicCourses = new ArrayList<>();
		
		for (Course course : coursesFromDb) {
			CoursePublicDTO sum = new CoursePublicDTO();
			modelMapper.map(course, sum);
			convertPublicCourses.add(sum);
		}
		
		
		return convertPublicCourses;
	}
	
	public Page<CoursePublicDTO> paginate(Pageable pageable, Integer tutorId) {
		List<CoursePublicDTO> convertListToPaginate = findAll(tutorId);
		
		Page<CoursePublicDTO> pageCoursePublicDTO = new PageImpl<>(convertListToPaginate, pageable, 0);
		
		return pageCoursePublicDTO;
	}
	
	public Course create(CourseDTO courseDTO, Integer tutorid) {
		Course course = new Course();
		
		modelMapper.map(courseDTO, course);
		
//		course.setName(courseDTO.getName());
//		course.setDescription(courseDTO.getDescription());
//		course.setCoverPath(courseDTO.getCoverPath());
//		course.setLevel(courseDTO.getLevel());
		course.setTechStack(null);
		course.setCreatedDate(LocalDateTime.now());
		course.setIsActive(true);
		course.setTutorId(GetTutorByTutorId(tutorid));
		
		Course SaveCourse = courseRepository.save(course);
		
		List<TechStack> tech_sum = saveTechStack(courseDTO,SaveCourse);
		

		
		SaveCourse.setTechStack(tech_sum);
		
		return SaveCourse;
	}

	private List<TechStack> saveTechStack(CourseDTO courseDTO, Course SaveCourse) {
		List<TechStack> tech_sum = new ArrayList<>();
		TechStack techStackNew = new TechStack();
		for (String techstack : courseDTO.getTechStack()) {
			techStackNew.setCourse(SaveCourse);
			techStackNew.setTechStack(techstack);
			tech_sum.add(techStackRepository.save(techStackNew));
		}
		return tech_sum;
	}

	public Course findById (Integer id, Integer tutorId) {
		return courseRepository.findByIdAndTutorId(id, GetTutorByTutorId(tutorId))
				.orElseThrow(ResourceNotFoundException::new);
	}
	
	public CoursePublicDTO findByIdDTO (Integer id, Integer tutorId) {
		Course courseFromDB = findById(id, tutorId);
		CoursePublicDTO convertCourseFromDBToCoursePublicDTO = new CoursePublicDTO();
		
		modelMapper.map(courseFromDB, convertCourseFromDBToCoursePublicDTO);
		
		return convertCourseFromDBToCoursePublicDTO;
	}
	
	public CoursePublicDTO update(Integer id, CourseDTO courseFormDTO, Integer tutorId) {
		Course courseFromDb = findById(id,tutorId);
		
		if (!courseFromDb.getTechStack().equals(courseFormDTO.getTechStack())) {
			deleteTechStack(courseFromDb);
			courseFromDb.setTechStack(null);
			List<TechStack> tech_sum = saveTechStack(courseFormDTO,courseFromDb);
			courseFromDb.setTechStack(tech_sum);
		}
		
		modelMapper.map(courseFormDTO, courseFromDb);
		courseFromDb.setUpdatedDate(LocalDateTime.now());
	//	courseFromDb.setTechStack(courseFormDTO.getTechStack());
		
		courseRepository.save(courseFromDb);
		
		
		return findByIdDTO(id, tutorId);
	}
	
	public void deleteTechStack(Course courseFromDB) {
		techStackRepository.deleteAllInBatch(courseFromDB.getTechStack());
	}
	
	public void delete (Integer id, Integer tutorId) {
		Course course = findById(id, tutorId);
		courseRepository.delete(course);
	}
	
}
