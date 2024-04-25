package com.project.firstclicks.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.firstclicks.dto.UserClientDTO;
import com.project.firstclicks.entity.Student;
import com.project.firstclicks.entity.Tutor;
import com.project.firstclicks.repository.user.RoleRepository;
import com.project.firstclicks.repository.user.StudentRepository;
import com.project.firstclicks.repository.user.TutorRepository;

//Aqui se almacenan los metodos de controller
@Service
public class UserService {
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired 
	TutorRepository tutorRepository;
	@Autowired
	StudentRepository studentRepository;
	
	public Tutor toTutor(UserClientDTO dto) {
		Tutor newTutor = new Tutor();
		
		newTutor.setUserName(dto.username());
		newTutor.setPassword(dto.password());
		newTutor.setFirstName(dto.firstname());
		newTutor.setLastName(dto.lastname());
		newTutor.setRole(roleRepository.getReferenceById(dto.roleId()));
		newTutor.setEmail(dto.email());
		newTutor.setAddress(dto.address());
		
		return newTutor;
	}
	
	public void addTutor(Tutor tutor) {
		tutorRepository.saveAndFlush(tutor);
	}
	
	public Student toStudent(UserClientDTO dto) {
		
		Student newStudent = new Student();
		
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
