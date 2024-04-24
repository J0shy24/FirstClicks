package com.project.firstclicks.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.firstclicks.entity.user.User;
import com.project.firstclicks.service.user.UserService;

//Con el API controller conectamos front con back.
@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	
	private final UserService userService;
	

	public UserApiController(UserService userService) {
		this.userService=userService;
	}
	
	
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User user){
		User newUser = userService.addUser(user);
		return new ResponseEntity<>(newUser,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> ListUsers(){
		List <User> users = userService.getUsers();
		return new ResponseEntity<>(users,HttpStatus.OK);
	}

}
