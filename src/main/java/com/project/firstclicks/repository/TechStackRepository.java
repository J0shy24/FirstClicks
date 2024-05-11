package com.project.firstclicks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.firstclicks.entity.TechStack;
import com.project.firstclicks.entityID.TechStackID;

public interface TechStackRepository extends JpaRepository<TechStack, TechStackID>{

}
