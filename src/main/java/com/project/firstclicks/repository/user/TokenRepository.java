package com.project.firstclicks.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.firstclicks.entity.Token;

public interface TokenRepository extends JpaRepository<Token,Integer>{
	Optional<Token> findByToken(String token);
}
