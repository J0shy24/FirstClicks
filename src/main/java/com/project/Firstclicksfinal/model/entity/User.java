package com.project.Firstclicksfinal.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToMany
    @JoinTable(
    		name = "enrollment",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    Set<Course> enrolledCourses;

    @Column(name = "firstname",nullable=false)
    private String firstName;

    @Column(name = "lastname",nullable=false)
    private String lastName;
    
    @Column(name = "username",nullable=false)
    private String userName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    @Column(name = "dateofbirth", nullable = false)
    private LocalDate dateOfBirth;    
    
    @Column(name = "photoroute")
    private String photoRoute;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;

    public enum Role {
        STUDENT,
        TUTOR,
        ADMIN
    }
}
