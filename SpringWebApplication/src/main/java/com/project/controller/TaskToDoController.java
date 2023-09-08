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

import com.project.model.TaskToDo;
import com.project.service.TaskToDoService;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("username")
public class TaskToDoController {

	private TaskToDoService taskToDoService;
	
	public TaskToDoController(TaskToDoService taskToDoService) {
		super();
		this.taskToDoService = taskToDoService;
	}

		
	@RequestMapping("taskToDoList")
	public String listAllTodos(ModelMap model) {
		List<TaskToDo> todos = taskToDoService.findByUsername("tanay");
		model.addAttribute("todos", todos);
		
		return "listTaskToDo";
	}
	
	@RequestMapping(value="addTaskToDo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username = (String) model.get("username");
		TaskToDo newTaskToDo = new TaskToDo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
		model.put("newTaskToDo", newTaskToDo);
		return "newTaskToDo";
	}
	
	@RequestMapping(value="addTaskToDo", method = RequestMethod.POST)
//	public String addNewTaskTodo(@RequestParam String description, ModelMap model) {
	public String addNewTaskTodo(ModelMap model,@Valid TaskToDo taskToDo, BindingResult result) {
		
		if(result.hasErrors())
		{
			return "newTaskToDo";
		}
		String username = (String)model.get("username");
		taskToDoService.addTaskToDo(username, taskToDo.getDescription(), 
				taskToDo.getTargetDate(), false);
		return "redirect:taskToDoList";
	}
	
	@RequestMapping("deleteTaskToDo")
	public String deleteTodo(@RequestParam int id) {
		taskToDoService.deleteById(id);
		return "redirect:taskToDoList";
	}
	
	@RequestMapping(value="updateTaskToDo", method = RequestMethod.GET)
	public String showUpdateTaskTodoPage(@RequestParam int id, ModelMap model) {
		TaskToDo newTaskToDo = taskToDoService.findById(id);
		model.addAttribute("newTaskToDo", newTaskToDo);
		return "newTaskToDo";
	}
	
	@RequestMapping(value="updateTaskToDo", method = RequestMethod.POST)
	public String updateTaskTodoPage(ModelMap model,@Valid TaskToDo taskToDo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		
		String username = (String)model.get("name");
		taskToDo.setUsername(username);
		taskToDoService.updateTodo(taskToDo);
		return "redirect:taskToDoList";
	}
}
