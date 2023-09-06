package com.project.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.project.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UserJPAEntityManagerRepository 
{
    @PersistenceContext
	private EntityManager entityManager;
    
    public void insert(User user)
    {
    	entityManager.merge(user);
    }
    
    public User findById(Long id)
	{
		return entityManager.find(User.class,id);
	}
    
    public void deleteById(Long id)
	{
		User user= entityManager.find(User.class,id);
		entityManager.remove(user);
	}
	
}
