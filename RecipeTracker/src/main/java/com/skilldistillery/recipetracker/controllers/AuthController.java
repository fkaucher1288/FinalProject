package com.skilldistillery.recipetracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.services.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authSvc;
	
	//TEMPORARY, DELETE LATER
	@GetMapping("usertest")
	public User userTest() {
		return authSvc.findUserByName("wolfgangPuck");
	}

}
