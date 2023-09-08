package com.project.service;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService 
{
	
public boolean authenticate(String username, String password) {
		
		boolean isValidUserName = username.equalsIgnoreCase("tanay");
		boolean isValidPassword = password.equalsIgnoreCase("Tanay@12345");
		return isValidUserName && isValidPassword;
	}

}
