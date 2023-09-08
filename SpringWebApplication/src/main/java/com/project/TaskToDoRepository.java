package com.project;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.entity.TaskToDo;

public interface TaskToDoRepository extends JpaRepository<TaskToDo,Integer>
{
  public List<TaskToDo> findByUsername(String username);
}
