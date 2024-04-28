package com.project.firstclicks.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.firstclicks.entity.AppUser;
import com.project.firstclicks.repository.user.UserRepository;

public class AppUserSecurityService implements UserDetailsService {
	 @Autowired
	 private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<AppUser> user = userRepository.findByUsername(username);
		
		if(user.isPresent()) {
			var userObj = user.get();
			
			return User.builder()
					.username(userObj.getUserName())
					.password(userObj.getPassword())
					.roles(userObj.getRole().getRoleName())
					.build();
		}else {
			throw new UsernameNotFoundException(username);
		}
	}

}
