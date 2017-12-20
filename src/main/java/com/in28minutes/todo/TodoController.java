package com.in28minutes.todo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	MySQLConnector service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	/**
	 * Retrieve the todos associated with particular user and send it to the view.
	 * TODO:Dynamique user and interact with DB
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	//Map this Controller with this URL
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	//@ResponseBody
	public String listTodos(ModelMap model) throws SQLException {
		model.addAttribute("todos", service.retrieveTodos(retrieveLoggedinUserName()));
		return "list-todos";
	}

	private String retrieveLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "todo";
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) throws SQLException {
		if (result.hasErrors()) {
			return "todo";
		}
		service.addTodo(retrieveLoggedinUserName(), todo.getDesc(), new Date(), false);
		//model.clear(); question: Why we do this?
		return "redirect:list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) throws SQLException {
		Todo todo = service.retrieveTodo(id);
		model.addAttribute("todo", todo);
		//model.clear(); 
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult result) throws SQLException {
		if (result.hasErrors()) {
			return "todo";
		}
		todo.setUser(retrieveLoggedinUserName());
		service.updateTodo(todo);
		//model.clear();
		return "redirect:list-todos";
	}
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) throws SQLException {
		service.deleteTodo(id);
		//model.clear();
		return "redirect:list-todos";
	}
	
}
