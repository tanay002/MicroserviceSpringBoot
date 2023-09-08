package com.project.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("username")

//For Spring Security Enabled i make changes in LoginController such as 
// renamed LoginController as WelcomeController and removed unrequired method from
//this controller 
public class LoginController {
	/*
	private AuthenticationService authenticationService;

	public  LoginController(AuthenticationService authenticationService)
	{
		this.authenticationService=authenticationService;
	}
	
	@RequestMapping("loginPage")
	public String gotoLoginPage() {
		return "login";
	}

	@RequestMapping(value="login",method = RequestMethod.POST)
	public String gotoWelcomePage(@RequestParam String username, 
			@RequestParam String password, ModelMap model) {
		
		if(authenticationService.authenticate(username, password)) {

			model.put("username", username);	
			return "welcome";
		}
		
		return "login";
	}
	*/
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String gotoWelcomePage(ModelMap model) {
		model.put("username", getLoggedinUsername());
		return "welcome";
	}
	
	private String getLoggedinUsername() {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
