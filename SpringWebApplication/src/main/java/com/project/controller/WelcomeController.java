package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class WelcomeController {

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
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("username",model.getAttribute("username"));
		return "welcome";
	}
}
