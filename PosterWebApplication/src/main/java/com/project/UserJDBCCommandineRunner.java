package com.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.repository.UserJDBCRepository;

@Component
public class UserJDBCCommandineRunner implements CommandLineRunner
{

	@Autowired
	private UserJDBCRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.insert();
		
	}

	
}
