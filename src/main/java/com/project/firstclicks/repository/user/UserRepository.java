package com.project.firstclicks.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.project.firstclicks.entity.User;

@NoRepositoryBean
public interface UserRepository extends JpaRepository<User, Integer> {

}
