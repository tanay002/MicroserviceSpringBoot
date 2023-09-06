package com.project;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.entity.User;
import com.project.repository.UserJDBCRepository;
import com.project.repository.UserJPAEntityManagerRepository;
import com.project.repository.UserJPARepository;

@Component
public class UserJDBCCommandineRunner implements CommandLineRunner
{

	//@Autowired
	//private UserJDBCRepository repository;
	
	//@Autowired
	//private UserJPAEntityManagerRepository repository;
	
	@Autowired
	private UserJPARepository repository;
	
	@Override
	public void run(String... args) throws Exception {
	/*  JDBC
	   
	    repository.insert();
		repository.insertByUserObject(new User(7,"Mahesh",LocalDate.now()));
		repository.insertByUserObject(new User(8,"Ravi",LocalDate.now()));
		repository.insertByUserObject(new User(9,"Shashi",LocalDate.now()));
		repository.insertByUserObject(new User(10,"Jatin",LocalDate.now()));
		repository.delete(2L);
		
		System.out.println(repository.findById(4L));
		
		
		JPA
		
		repository.insert(new User(6,"Mahesh",LocalDate.now()));
     	repository.insert(new User(7,"Ravikantt",LocalDate.now()));
		repository.deleteById(2L);
			System.out.println(repository.findById(4L));
		*/
		
		System.out.println(repository.findById(2));
		System.out.println(repository.findByName("Deepak_Verma"));
		System.out.println(repository.count());
		repository.deleteById(2);
		System.out.println(repository.findAll());
		System.out.println(repository.count());
		
	}

	
}
