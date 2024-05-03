package com.project.firstclicks.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Id
	@ManyToOne
	@JoinColumn(name="tutor_id")
	private Tutor tutor;
	@Column(nullable=false)
	private String name;
	private Boolean isActive;
	@Column(nullable=false)
	private String description;
	@CreatedDate
	@Column(nullable=false,updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdDate;
	@Formula(value="SELECT COUNT(*) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id")
	private Integer studentNumber;
	@Formula(value="SELECT COUNT(student_review) FROM student_course AS sc WHERE tutor_id=sc.tutor_id AND id=sc.id AND student_review IS NOT NULL")
	private Integer numberReviews;
}
