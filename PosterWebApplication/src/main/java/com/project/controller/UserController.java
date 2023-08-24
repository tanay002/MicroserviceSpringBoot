package com.project.controller;

import java.net.URI;
import java.util.List;

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
import com.project.service.UserService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import jakarta.validation.Valid;

@RestController()
public class UserController {

	private UserService userService;

	public UserController(UserService userService)
	{
		this.userService=userService;
	}

	@GetMapping(path="/users/all")
	public List<User> retrieveAllUsers()
	{
		return userService.retrieveAllUsers();
	}

	@GetMapping(path="/users/{id}")
	public User findById(@PathVariable Long id)
	{
		User user=userService.findById(id);
		if(user==null)
			throw new UserNotFoundException("id"+id); 
		return user;
	}

	@PostMapping(path="/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User userSaved =userService.saveUser(user);
		URI locatiion= ServletUriComponentsBuilder.fromCurrentRequest()		
				.path("/{id}")
				.buildAndExpand(userSaved.getId())
				.toUri();
		return ResponseEntity.created(locatiion).build();
	}
	
	@DeleteMapping(path="users/{id}")
	public void deleteUser(@PathVariable Long id)
	{
		userService.deleteUser(id);	
	}
	
	@GetMapping(path="/users/hateous/{id}")
	public EntityModel<User>  findByIdHateous(@PathVariable Long id) throws UserNotFoundException
	{
		User user=userService.findById(id);
		EntityModel<User> entityModel= EntityModel.of(user);
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
}
