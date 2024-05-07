package com.project.Firstclicksfinal.model.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Course implements Serializable{
	private static final long serialVersionUID = -866012554390263651L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToMany(mappedBy = "enrolledCourses")
    Set<User> Students;
    
    @Column(nullable=false)
    private String title;
    
    @Column(nullable = false)
    private String description;
    
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private String coverPath;
    private Integer tutorId;
    
    @Enumerated(EnumType.STRING)
    private Level level;
    
    @OneToMany (mappedBy = "course", cascade = CascadeType.ALL)
    private List<TechStack> techStack;
    
    
    public enum Level {
        ENTRY,
        INTERMEDIATE,
        ADVANCE
    }
}
