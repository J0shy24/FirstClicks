package com.project.firstclicks.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.firstclicks.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>{

}
