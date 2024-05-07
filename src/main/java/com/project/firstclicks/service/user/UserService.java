package com.project.firstclicks.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.firstclicks.dto.UserClientDTO;
import com.project.firstclicks.entity.Student;
import com.project.firstclicks.entity.Tutor;
import com.project.firstclicks.entity.AppUser;
import com.project.firstclicks.repository.user.RoleRepository;
import com.project.firstclicks.repository.user.StudentRepository;
import com.project.firstclicks.repository.user.TutorRepository;

//Aqui se almacenan los metodos de controller
@Service
public class UserService{
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired 
	TutorRepository tutorRepository;
	@Autowired
	StudentRepository studentRepository;
//	@Autowired
//	PasswordEncoder passwordEncoder;
	
	public Tutor toTutor(UserClientDTO dto) {
		Tutor newTutor = new Tutor();
		
		newTutor.setUsername(dto.username());
		//newTutor.setPassword(passwordEncoder.encode(dto.password()));
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
		
		newStudent.setUsername(dto.username());
		//newStudent.setPassword(passwordEncoder.encode(dto.password()));
		newStudent.setFirstName(dto.firstname());
		newStudent.setLastName(dto.lastname());
		newStudent.setRole(roleRepository.getReferenceById(dto.roleId()));
		newStudent.setEmail(dto.email());
		newStudent.setAddress(dto.address());
		
		return newStudent;
	}
}
