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
import jakarta.validation.Valid;

@Controller
@SessionAttributes("username")
public class TaskToDoControllerJPA {

	private TaskToDoRepository todoRepository;

	public TaskToDoControllerJPA(TaskToDoRepository todoRepository) {
		super();
		this.todoRepository=todoRepository;
	}


	@RequestMapping("taskToDoList")
	public String listAllTodos(ModelMap model) {
		String username=getLoggedInUsername(model);
		List<TaskToDo> todos = todoRepository.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTaskToDo";
	}

	@RequestMapping(value="addTaskToDo", method = RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username=getLoggedInUsername(model);
		TaskToDo toDo = new TaskToDo();
		model.put("toDo", toDo);
		return "newTaskToDo";
	}

	@RequestMapping(value="addTaskToDo", method = RequestMethod.POST)
	public String addNewTaskTodo(ModelMap model,@Valid TaskToDo toDo, BindingResult result) {

		if(result.hasErrors()) {
			return "newTaskToDo";
		}

		String username = getLoggedInUsername(model);
		toDo.setUsername(username);
		todoRepository.save(toDo);
		return "redirect:taskToDoList";
	}

	@RequestMapping("deleteTaskToDo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:taskToDoList";
	}

	@RequestMapping(value="updateTaskToDo", method = RequestMethod.GET)
	public String showUpdateTaskTodoPage(@RequestParam int id, ModelMap model) {
		TaskToDo toDo = todoRepository.findById(id).get();
		model.addAttribute("toDo", toDo);
		return "newTaskToDo";
	}

	@RequestMapping(value="updateTaskToDo", method = RequestMethod.POST)
	public String updateTaskTodoPage(ModelMap model,@Valid TaskToDo toDo, BindingResult result) {
		if(result.hasErrors()) {
			return "newTaskToDo";
		}

		String username = getLoggedInUsername(model);
		toDo.setUsername(username);
		todoRepository.save(toDo);
	//	model.remove("newTaskToDo");
		return "redirect:taskToDoList";
	}

	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = 
				SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();

	}
}
