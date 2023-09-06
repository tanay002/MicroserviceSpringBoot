package com.project.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.Course;


@RestController
public class CourseController {

	@GetMapping("/all")
	public List<Course> retrieveAllCourses()
	{
		return Arrays.asList(new Course(1,"MCA","SCSIT"),
				new Course(2,"MBA","IIPS"),
				new Course(3,"BBA","IMS")
				);
		
	}
	
}
