package com.project.firstclicks.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import com.project.firstclicks.dto.CoursePublicDTO;
import com.project.firstclicks.dto.StudentCourseDTO;
import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entity.Student;
import com.project.firstclicks.entity.StudentCourse;
import com.project.firstclicks.exceptionhandler.BadRequestException;
import com.project.firstclicks.exceptionhandler.ResourceNotFoundException;
import com.project.firstclicks.repository.CourseRepository;
import com.project.firstclicks.repository.StudentCourseRepository;
import com.project.firstclicks.repository.StudentRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentCourseAdminService {
	private CourseRepository courseRepository;
	private StudentCourseRepository studentCourseRepository;
	private StudentRepository studentRepository;
	private ModelMapper modelMapper;
	
	
	public List<StudentCourseDTO> findAll(Integer studentId) {
		List<StudentCourse> allCoursesofStudent = studentCourseRepository.findAllByStudentId(studentId);
		List<StudentCourseDTO> publicCourseStudentDTO = new ArrayList<>();
		
		for (StudentCourse studentCourse : allCoursesofStudent) {
			publicCourseStudentDTO.add(convertStudentCourseToCourseDTO(studentCourse)); 
		}
		
		return publicCourseStudentDTO;
	}


	public StudentCourseDTO enroll(Integer courseId, Integer idAccess) {
		boolean existsEnroll = studentCourseRepository.existsByCourseIdAndStudentId(courseId, idAccess);
		
		if (existsEnroll) {
			throw new BadRequestException("Ya está apuntado a este curso");
		}
		
		StudentCourse enrollment = new StudentCourse();
		Course enrollCourse = courseRepository.findById(courseId)
								.orElseThrow(ResourceNotFoundException::new);
		
		Student enrollStudent = studentRepository.findById(idAccess)
								.orElseThrow(ResourceNotFoundException::new);
		
		enrollment.setCourse(enrollCourse);
		enrollment.setStudent(enrollStudent);
		enrollment.setStudentActive(true);
		enrollment.setStudentReview(null);
		enrollment.setStudentStars(null);
		enrollment.setStudentJoinDate(LocalDateTime.now());
		
		StudentCourse saveStudentCourse = studentCourseRepository.save(enrollment);
		
		StudentCourseDTO courseLimit = convertStudentCourseToCourseDTO(saveStudentCourse);
		
		return courseLimit;
	}


	private StudentCourseDTO convertStudentCourseToCourseDTO(StudentCourse saveStudentCourse) {
		StudentCourseDTO studentCourseDTOReturn = new StudentCourseDTO();
		CoursePublicDTO converDbToDTO = new CoursePublicDTO();
		Course studentCourse = saveStudentCourse.getCourse();
		
//		converDbToDTO.setCoverPath(studentCourse.getCoverPath());
//		converDbToDTO.setDescription(studentCourse.getDescription());
//		converDbToDTO.setLevel(studentCourse.getLevel());
//		converDbToDTO.setName(studentCourse.getName());
//		converDbToDTO.setTechStack(studentCourse.getTechStack());
		
		modelMapper.map(studentCourse, converDbToDTO);
		modelMapper.map(saveStudentCourse, studentCourseDTOReturn);
		
	//	studentCourseDTOReturn.setCourseDTOInterAccess(converDbToDTO);
		studentCourseDTOReturn.setCourseEnrolled(converDbToDTO);
		
		return studentCourseDTOReturn;
	}


	public void leave(Integer courseId, Integer idAccess) {
		StudentCourse LeaveCourse = studentCourseRepository.findByCourseIdAndStudentId(courseId, idAccess)
									.orElseThrow(ResourceNotFoundException::new);
		
		studentCourseRepository.delete(LeaveCourse);
	}
}
