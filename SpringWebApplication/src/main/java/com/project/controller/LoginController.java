package com.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.service.AuthenticationService;

@Controller
@SessionAttributes("username")
public class LoginController {

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
}
