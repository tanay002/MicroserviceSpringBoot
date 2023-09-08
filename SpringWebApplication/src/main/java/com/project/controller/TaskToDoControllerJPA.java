package com.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.TaskToDoRepository;
import com.project.entity.TaskToDo;
import com.project.service.TaskToDoService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("username")
public class TaskToDoControllerJPA {

	private TaskToDoService taskToDoService;
	private TaskToDoRepository todoRepository;
	
	public TaskToDoControllerJPA(TaskToDoService taskToDoService,TaskToDoRepository todoRepository) {
		super();
		this.taskToDoService = taskToDoService;
		this.todoRepository=todoRepository;
	}

		
	@RequestMapping("taskToDoList")
	public String listAllTodos(ModelMap model) {
		//	String username=(String)model.getAttribute("username");
		String username=getLoggedInUsername(model);
		//List<TaskToDo> todos = taskToDoService.findByUsername(username);
		List<TaskToDo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);

		return "listTaskToDo";
	}
	
	@RequestMapping(value="addTaskToDo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		//String username = (String) model.get("username");
		  String username=getLoggedInUsername(model);
		TaskToDo newTaskToDo = new TaskToDo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
		model.put("newTaskToDo", newTaskToDo);
		return "newTaskToDo";
	}
	
	@RequestMapping(value="addTaskToDo", method = RequestMethod.POST)
//	public String addNewTaskTodo(@RequestParam String description, ModelMap model) {
	public String addNewTaskTodo(ModelMap model,@Valid TaskToDo taskToDo, BindingResult result) {
		
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = getLoggedInUsername(model);
		taskToDo.setUsername(username);
		//taskToDo.setId(17);
		todoRepository.save(taskToDo);
	
		return "redirect:taskToDoList";
	}
	
	@RequestMapping("deleteTaskToDo")
	public String deleteTodo(@RequestParam int id) {
		//taskToDoService.deleteById(id);
		todoRepository.deleteById(id);
		return "redirect:taskToDoList";
	}
	
	@RequestMapping(value="updateTaskToDo", method = RequestMethod.GET)
	public String showUpdateTaskTodoPage(@RequestParam int id, ModelMap model) {
	//	TaskToDo newTaskToDo = taskToDoService.findById(id);
		TaskToDo newTaskToDo = todoRepository.findById(id).get();
		model.addAttribute("newTaskToDo", newTaskToDo);
		return "newTaskToDo";
	}
	
	@RequestMapping(value="updateTaskToDo", method = RequestMethod.POST)
	public String updateTaskTodoPage(ModelMap model,@Valid TaskToDo taskToDo, BindingResult result) {
		if(result.hasErrors()) {
			return "newTaskToDo";
		}
		
		String username = getLoggedInUsername(model);
		taskToDo.setUsername(username);
		todoRepository.save(taskToDo);
		return "redirect:taskToDoList";
	}
	
	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();

	}
}
