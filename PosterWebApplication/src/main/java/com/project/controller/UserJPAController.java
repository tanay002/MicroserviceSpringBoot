package com.project.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.entity.User;
import com.project.exception.UserNotFoundException;
import com.project.repository.UserJPARepository;
import com.project.service.UserJPAService;
import com.project.service.UserService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import jakarta.validation.Valid;

@RestController()
public class UserJPAController {

	private UserJPAService userJPAService;
	private UserJPARepository userJPARepository;

	public UserJPAController(UserJPAService userJPAService,UserJPARepository userJPARepository)
	{
		this.userJPAService=userJPAService;
		this.userJPARepository=userJPARepository;
	}

	@GetMapping(path="/jpa/users/all")
	public List<User> retrieveAllUsers()
	{
		return userJPAService.retrieveAllUsers();
	}

	@GetMapping(path="/jpa/users/{id}")
	public User findById(@PathVariable Integer id)
	{
		User user=userJPAService.findById(id);
		if(user==null)
			throw new UserNotFoundException("id"+id); 
		return user;
	}
//Issue
	@PostMapping(path="/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User userSaved =userJPAService.saveUser(user);
		URI locatiion= ServletUriComponentsBuilder.fromCurrentRequest()		
				.path("/{id}")
				.buildAndExpand(userSaved.getId())
				.toUri();
		return ResponseEntity.created(locatiion).build();
	}
	
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteUser(@PathVariable Integer id)
	{
		userJPAService.deleteUser(id);	
	}
	
	@GetMapping(path="/jpa/users/hateous/{id}")
	public EntityModel<User>  findByIdHateous(@PathVariable Integer id) throws UserNotFoundException
	{
		Optional<User> user=userJPARepository.findById(id);
		if(user.isEmpty())
		throw new UserNotFoundException("User doesn't exists");
		EntityModel<User> entityModel= EntityModel.of(user.get());
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@GetMapping(path="/jpa/users/countUser")
	public Long findTotalUser()
	{
		return userJPAService.findTotalUser();
	}
	
	@GetMapping(path="/jpa/users/name/{name}")
	public List<User> findUserByName(@PathVariable String name)
	{
		return userJPAService.findByName(name);
	}
}
