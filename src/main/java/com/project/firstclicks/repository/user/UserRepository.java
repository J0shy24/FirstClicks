package com.project.firstclicks.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.project.firstclicks.entity.AppUser;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<AppUser, Integer> {

		Optional<AppUser> findByUsername(String username);
}
