package com.project.firstclicks.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@Table(name="students")
public class Student extends Client{
	private static final long serialVersionUID = 1798476854278608136L;
}
