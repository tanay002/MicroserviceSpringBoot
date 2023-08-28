package com.project.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.entity.User;

@Repository
public class UserDao {

	private static List<User> users=new ArrayList<User>();
	private static Integer userCount=0;

	static 
	{
		users.add(new User(++userCount,"Tanay Saxena",LocalDate.now().minusYears(30)));
		users.add(new User(++userCount,"Ravi Sharma",LocalDate.now().minusYears(15)));
		users.add(new User(++userCount,"Rahul Vashisth",LocalDate.now().minusYears(20)));
	}

	public List<User> retrieveAllUsers()
	{
		return users;
	}

	public User findById(Integer id)
	{
		return users.stream().filter(user->user.getId().equals(id)).findFirst().orElse(null);
	}

	public User saveUser(User user)
	{ 
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	public void deleteUser(Long id)
	{
		users.removeIf(user->user.getId().equals(id));	
	}
}
