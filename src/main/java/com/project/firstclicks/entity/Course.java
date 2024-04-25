package com.project.firstclicks.entity;

import java.io.Serializable;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="courses")
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
	private String name;
	private Boolean isActive;
	private String description;
	@Formula(value="SELECT COUNT(*) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id")
	private Integer studentNumber;
	@Formula(value="SELECT COUNT(student_review) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id AND student_review IS NOT NULL")
	private Integer numberReviews;
}
