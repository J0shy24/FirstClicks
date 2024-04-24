package com.project.firstclicks.service.user;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.firstclicks.entity.user.User;
import com.project.firstclicks.repository.user.UserRepository;

@Service
public class UserService {
	
	
	private final UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
    public List<User> getUsers() {
        return userRepository.findAll();
    }
	
}
