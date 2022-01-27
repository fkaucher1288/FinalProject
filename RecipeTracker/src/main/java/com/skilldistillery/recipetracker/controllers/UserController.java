package com.skilldistillery.recipetracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class UserController {

	@Autowired
	private UserService userSvc;
	
	@GetMapping("users")
	public List<User> index() {
		return userSvc.getAllUsers();
	}
	
	@GetMapping("users/{id}")
	public User getUserById(HttpServletResponse response, @PathVariable int id) {
		User user = userSvc.getUserById(id);
		if(user == null) {
			response.setStatus(404);
		} return user;
	}
	
	
}
