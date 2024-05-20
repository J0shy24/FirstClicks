package com.project.firstclicks.entity;

import java.io.Serializable;
import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="student_course")
public class StudentCourse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5811848091454727495L;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="course_id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	@Column(nullable=false)
	private Boolean studentActive;
	private LocalDateTime studentJoinDate;
	private String studentReview;
	private Integer studentStars;
}
