package com.project.firstclicks.entity.role;

import java.io.Serializable;
import java.util.List;

import com.project.firstclicks.entity.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="roles")
public class Role implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1263326067628401509L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String roleCode;
	private String roleName;
	@OneToMany(
		mappedBy="role"	
			)
	private List<User> users;
	
}
