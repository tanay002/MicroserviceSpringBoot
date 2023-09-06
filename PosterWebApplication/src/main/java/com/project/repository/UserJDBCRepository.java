package com.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.entity.User;

@Repository
public class UserJDBCRepository {

	@Autowired
	JdbcTemplate springJDBCTemplate;
	
	private static String INSERT_QUERY=
			""" 
			insert into user_details (id,name,birth_date) values
			(6,'Tarunesh','2000-10-10');	
			""";
	
	private static String INSERT_QUERY_BY_USER_OBJECT=
			""" 
			insert into user_details (id,name,birth_date) values
			(?,?,?);	
			""";
	
	private static String DELETE_USER_BY_ID=
			""" 
			delete from user_details where id=?;	
			""";
	
	private static String SELECT_QUERY=
			""" 
			Select * from user_details where id=?;	
			""";
	
	public void insert()
	{
		springJDBCTemplate.update(INSERT_QUERY);
	}
	
	public void delete(Long id)
	{
		springJDBCTemplate.update(DELETE_USER_BY_ID,id);
	}
	
	public void insertByUserObject(User user)
	{
		springJDBCTemplate.update(INSERT_QUERY_BY_USER_OBJECT,user.getId(),user.getName(),user.getBirthDate());
	}
	
	public User findById(Long id)
	{
		return springJDBCTemplate.queryForObject(SELECT_QUERY,new BeanPropertyRowMapper<>(User.class), id);
	}
}
