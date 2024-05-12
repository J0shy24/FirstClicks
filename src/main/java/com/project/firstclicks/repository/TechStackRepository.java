package com.project.firstclicks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.firstclicks.entity.TechStack;
import com.project.firstclicks.entityID.TechStackID;
@Repository
public interface TechStackRepository extends JpaRepository<TechStack, TechStackID>{

}
