package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("indexPage")
	@ResponseBody
	public String index()
	{
		return "Welcome to Index Page";
	}
	
	@RequestMapping("homePage")
	public String homePage()
	{
		return "home";
	}
}
