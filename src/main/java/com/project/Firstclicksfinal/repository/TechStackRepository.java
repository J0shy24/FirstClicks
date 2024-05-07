package com.project.Firstclicksfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Firstclicksfinal.model.entity.Course;
import com.project.Firstclicksfinal.model.entity.TechStack;
import com.project.Firstclicksfinal.model.entityID.TechStackID;


public interface TechStackRepository extends JpaRepository<TechStack, TechStackID>{

}
