package com.project.firstclicks.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.firstclicks.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{
	boolean existsByEmail(String email);
}
