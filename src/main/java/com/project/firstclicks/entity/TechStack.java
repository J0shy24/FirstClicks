package com.project.firstclicks.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.firstclicks.entityID.TechStackID;

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
@Table(name = "techstack")
@IdClass(TechStackID.class)
public class TechStack {
	@Id
	@JsonIgnore
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "course_id", referencedColumnName = "id"),
        @JoinColumn(name = "tutor_id", referencedColumnName = "tutor_id")
    })

    private Course course;
 	
	@Id
	@Column(nullable = false)
	private String techStack;
	

}
