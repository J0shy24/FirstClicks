package com.project.Firstclicksfinal.controller;

import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.JwkSetUriJwtDecoderBuilderCustomizer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.Firstclicksfinal.config.TokenResponse;
import com.project.Firstclicksfinal.model.dto.CourseFormDTO;
import com.project.Firstclicksfinal.model.entity.Course;
import com.project.Firstclicksfinal.service.TutorCourseAdminService;


import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;


@RequestMapping("/api/tutor/courses")
@AllArgsConstructor
@RestController
public class TutorCourseAdminController {
	private TutorCourseAdminService tutorCourseAdminService;

	
	@GetMapping("/list")
	public List<Course> list(HttpServletRequest request) {
		return tutorCourseAdminService.findAll(getIdAccess(request));

	}
	
	@GetMapping
	public Page<Course> paginate(@PageableDefault(size = 5, sort = "title") Pageable pageable) {
		return tutorCourseAdminService.paginate(pageable);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Course create(@RequestBody @Validated CourseFormDTO courseFormDTO, HttpServletRequest request) {
		return tutorCourseAdminService.create(courseFormDTO, getIdAccess(request));
	}
	
	@GetMapping("/{id}")
	public Course get(@PathVariable Integer id,HttpServletRequest request) {
		return tutorCourseAdminService.findById(id,getIdAccess(request));
	}
	
	@PutMapping("/{id}")
	public Course update(@PathVariable Integer id, @RequestBody @Validated CourseFormDTO courseFormDTO,HttpServletRequest request) {
		return tutorCourseAdminService.update(id, courseFormDTO, getIdAccess(request));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id,HttpServletRequest request) {
		tutorCourseAdminService.delete(id,getIdAccess(request));
	}
	
	
	public Integer getIdAccess (HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		String payload = "";
		Base64.Decoder decoder = Base64.getUrlDecoder();

		//Capturar el Token
		if (bearerToken.startsWith("Bearer ")) {
			//Quita el Bearer
			String token = bearerToken.substring(7);
			
			//Divide el token en header y payload
			String[] chunks = token.split("\\.");
			
			String header = new String(decoder.decode(chunks[0]));
			payload = new String(decoder.decode(chunks[1]));
			
			//Convierte en Objeto el JSON
			ObjectMapper mapper = new ObjectMapper();
			TokenResponse TokenPayload;
			try {
				TokenPayload = mapper.readValue(payload, TokenResponse.class);
				payload = TokenPayload.getSub();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return tutorCourseAdminService.findIdByEmail(payload);
	}
}
