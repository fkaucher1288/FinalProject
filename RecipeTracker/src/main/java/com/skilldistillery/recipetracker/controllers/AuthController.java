package com.skilldistillery.recipetracker.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.services.AuthService;

@RestController
@CrossOrigin({"*", "http://localhost:4300"})
public class AuthController {

	@Autowired
	private AuthService authSvc;

	// TEMPORARY, DELETE LATER
	@GetMapping("usertest")
	public User userTest() {
		return authSvc.findUserByName("wolfgangPuck");
	}

	@PutMapping("/register")
	public User register(@RequestBody User user, HttpServletResponse res) throws Exception {
		if (user == null) {
			res.sendError(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		return authSvc.register(user);
	}

	@GetMapping("/authenticate")
	public User authenticate(Principal principal) {
		return authSvc.findUserByName(principal.getName());
	}

}
