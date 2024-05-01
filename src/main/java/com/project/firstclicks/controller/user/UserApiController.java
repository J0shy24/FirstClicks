package com.project.firstclicks.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.firstclicks.dto.UserClientDTO;
import com.project.firstclicks.entity.Tutor;
import com.project.firstclicks.repository.user.RoleRepository;
import com.project.firstclicks.repository.user.TutorRepository;

//Con el API controller conectamos front con back.
//Aqui se hace el Registration
@RestController
public class UserApiController {
	
	@Autowired
	UserService userService;

	//Register Tutor
	@PostMapping(UserWebMapController.USER_API_NEW_TUTOR)
	public @ResponseBody ResponseEntity<UserClientDTO> newTutor(@RequestBody UserClientDTO dto){
		Tutor newTutor = userService.toTutor(dto);
		userService.addTutor(newTutor);
		return  new ResponseEntity<>(dto,HttpStatus.OK);
	}
}
