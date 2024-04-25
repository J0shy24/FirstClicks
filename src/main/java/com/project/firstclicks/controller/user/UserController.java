package com.project.firstclicks.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.project.firstclicks.dto.UserClientDTO;
import com.project.firstclicks.entity.Student;
import com.project.firstclicks.entity.Tutor;
import com.project.firstclicks.repository.user.RoleRepository;
import com.project.firstclicks.repository.user.StudentRepository;
import com.project.firstclicks.repository.user.TutorRepository;

//Aqui se almacenan los metodos de controller
@Controller
public class UserController {
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired 
	TutorRepository tutorRepository;
	@Autowired
	StudentRepository studentRepository;
	
	private Tutor toTutor(UserClientDTO dto) {
		Tutor newTutor = new Tutor();
		
		newTutor.setId(dto.id());
		newTutor.setUserName(dto.username());
		newTutor.setPassword(dto.password());
		newTutor.setFirstName(dto.firstname());
		newTutor.setLastName(dto.lastname());
		newTutor.setRole(roleRepository.getReferenceById(dto.roleId()));
		newTutor.setEmail(dto.email());
		newTutor.setAddress(dto.address());
		
		return newTutor;
	}
	
	private Student toStudent(UserClientDTO dto) {
		
		Student newStudent = new Student();
		
		newStudent.setId(dto.id());
		newStudent.setUserName(dto.username());
		newStudent.setPassword(dto.password());
		newStudent.setFirstName(dto.firstname());
		newStudent.setLastName(dto.lastname());
		newStudent.setRole(roleRepository.getReferenceById(dto.roleId()));
		newStudent.setEmail(dto.email());
		newStudent.setAddress(dto.address());
		
		return newStudent;
	}
}
