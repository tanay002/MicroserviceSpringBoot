package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.dao.UserDao;
import com.project.entity.User;
import com.project.repository.UserJPARepository;

@Service
public class UserJPAService
{	
	public UserJPARepository userJPARepository;

	public UserJPAService(UserJPARepository userJPARepository)
	{
		this.userJPARepository=userJPARepository;
	}

	public List<User> retrieveAllUsers()
	{
		return userJPARepository.findAll();
	}


	public User findById(Integer id)
	{
		return userJPARepository.findById(id).orElse(null);
	}
	
	public User saveUser(User user)
	{ 
		return userJPARepository.save(user);
	}
	
	public void deleteUser(Integer id)
	{
		userJPARepository.deleteById(id);
	}
	
	public Long findTotalUser()
	{
		return userJPARepository.count();
	}
	
	public List<User> findByName(String name)
	{
		return userJPARepository.findByName(name);
	}
}
