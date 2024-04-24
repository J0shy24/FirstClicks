package com.project.firstclicks.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.firstclicks.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
