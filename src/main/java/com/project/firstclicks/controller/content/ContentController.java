package com.project.firstclicks.controller.content;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

	
	@GetMapping("/home")
	public String homePage() {
		return null;
		
	}
	
	@GetMapping("/admin/home")
	public String adminPage() {
		return null;
		
	}
	
	@GetMapping("/tutor/home")
	public String tutorPage() {
		return null;
		
	}
	
	@GetMapping("/student/home")
	public String studentPage() {
		return null;
		
	}
}
