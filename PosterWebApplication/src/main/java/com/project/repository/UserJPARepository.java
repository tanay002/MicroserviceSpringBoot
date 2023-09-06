package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.entity.User;

public interface UserJPARepository extends JpaRepository<User, Integer>
{
	List<User> findByName(String name);
}
