package com.project.firstclicks.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.firstclicks.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
