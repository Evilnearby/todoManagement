package com.in28minutes.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
	//
	@Autowired
	UserValidationService service;
	
	//Map this Controller with this URL
	//The whole URL: localhost:8080/spring-mvc/login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	//@ResponseBody
	public String showLoginPage() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleLoginRequest(@RequestParam String name, @RequestParam String password, ModelMap model) {
		if (!service.isUserValid(name, password)) {
			model.put("errorMessage", "Invalid Credentials");
			return "login";
		}
		//To make the values available in the view, we put it in the model
		model.put("name", name);
		model.put("password", password);
		return "welcome";
	}
}
