package com.project.firstclicks.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.Formula;

import com.project.firstclicks.entityIDs.CourseID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="courses")
@IdClass(CourseID.class)
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -866012554390263651L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Id
	@ManyToOne
	@JoinColumn(name="tutor_id")
	private Tutor tutor;
	@Column(nullable=false)
	private String name;
	private String coverPath;
	private Boolean isActive;
	@Column(nullable=false)
	private String description;
	
//	@OneToMany
//	@JoinColumns({
//		@JoinColumn(name="tutor_id", referencedColumnName = "tutor_id"),
//		@JoinColumn(name="id", referencedColumnName = "id")
//	})
//	@Formula(value="SELECT COUNT(*) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id")
	private Integer studentNumber;
//	
//	//@Formula(value="SELECT COUNT(*) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id")
//	@Formula(value="SELECT COUNT(*) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id")
	private Integer numberReviews;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
