package com.project.firstclicks.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name="courses")
@EntityListeners(AuditingEntityListener.class)
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -866012554390263651L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="tutor_id")
    private Tutor tutorId;
	
	@Column(nullable=false)
	private String name;
	private Boolean isActive;
	@Column(nullable=false)
	private String description;
	@CreatedDate
	@Column(nullable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime UpdatedDate;
	
	private String coverPath;
	
	@OneToMany (mappedBy = "course", cascade = CascadeType.ALL)
	private List<TechStack> techStack;
	
	@OneToMany(mappedBy = "course")
	Set<StudentCourse> enrollments;
	
    @Enumerated(EnumType.STRING)
    private Level level;
	
	public enum Level {
	        ENTRY,
	        INTERMEDIATE,
	        ADVANCE
	 }
	
//	@Formula(value="SELECT COUNT(*) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id")
//	private Integer studentNumber;
//	@Formula(value="SELECT COUNT(student_review) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id AND student_review IS NOT NULL")
//	private Integer numberReviews;
}
