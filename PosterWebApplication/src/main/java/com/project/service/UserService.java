package com.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.dao.UserDao;
import com.project.entity.User;

@Service
public class UserService
{
	public UserDao userDao;

	public UserService(UserDao userDao)
	{
		this.userDao=userDao;
	}

	public List<User> retrieveAllUsers()
	{
		return userDao.retrieveAllUsers();
	}


	public User findById(Integer id)
	{
		return userDao.findById(id);
	}
	
	public User saveUser(User user)
	{ 
		return userDao.saveUser(user);
	}
	
	public void deleteUser(Long id)
	{
	   userDao.deleteUser(id);
	}
}
