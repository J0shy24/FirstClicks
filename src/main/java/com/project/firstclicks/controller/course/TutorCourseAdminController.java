package com.project.firstclicks.controller.course;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.firstclicks.dto.CourseFormDTO;
import com.project.firstclicks.entity.Course;
import com.project.firstclicks.entityIDs.CourseID;
import com.project.firstclicks.repository.course.CourseRepository;
import com.project.firstclicks.service.course.TutorCourseAdminService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/tutor/courses")
@AllArgsConstructor
@RestController
public class TutorCourseAdminController {
	
	private TutorCourseAdminService tutorCourseAdminService;
	
	@GetMapping("/list")
	public List<Course> list() {
		return tutorCourseAdminService.findAll();
	}
	
	@GetMapping
	public Page<Course> paginate(@PageableDefault(size = 5, sort = "name") Pageable pageable) {
		return tutorCourseAdminService.paginate(pageable);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Course create(@RequestBody @Validated CourseFormDTO courseFormDTO) {
		return tutorCourseAdminService.create(courseFormDTO);
	}
	
	@GetMapping("/{id}")
	public Course get(@PathVariable CourseID id,@RequestBody @Validated CourseFormDTO courseFormDTO) {
		return tutorCourseAdminService.update(id, courseFormDTO);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable CourseID id) {
		tutorCourseAdminService.delete(id);
	}

}
