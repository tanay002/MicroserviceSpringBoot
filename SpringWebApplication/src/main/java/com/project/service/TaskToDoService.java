package com.project.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.project.entity.TaskToDo;

@Service
public class TaskToDoService {

	private static List<TaskToDo> todos = new ArrayList<TaskToDo>();
	private static int count=0;
/*	static {
		todos.add(new TaskToDo(++count, "Tanay","Learn Spring Framework 6", 
							LocalDate.now(), false ));
		todos.add(new TaskToDo(++count, "Tanay","Learn AWS", 
				LocalDate.now().plusYears(2), false ));
		todos.add(new TaskToDo(++count, "Tanay","Learn Full Stack Development", 
				LocalDate.now().plusYears(3), false ));
		todos.add(new TaskToDo(++count, "Rahul","React Js", 
				LocalDate.now().plusYears(3), false ));
		
	}
	*/
	public List<TaskToDo> findByUsername(String username){
		Predicate<? super TaskToDo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	
	public void addTaskToDo(String username, String description, LocalDate targetDate, boolean done) {
		TaskToDo todo = new TaskToDo(++count,username,description,targetDate,done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		Predicate<? super TaskToDo > predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}
	
	public TaskToDo findById(int id) {
		Predicate<? super TaskToDo> predicate = todo -> todo.getId() == id;
		TaskToDo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	public void updateTodo(TaskToDo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
	
	
}
