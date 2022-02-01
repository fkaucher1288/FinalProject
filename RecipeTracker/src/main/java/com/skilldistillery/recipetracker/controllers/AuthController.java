package com.skilldistillery.recipetracker.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.services.AuthService;
import com.skilldistillery.recipetracker.services.UserService;

@RestController
@CrossOrigin({"*", "http://localhost"})
public class AuthController {

	@Autowired
	private AuthService authSvc;
	
	@Autowired
	private UserService userSvc;

	@GetMapping("api/usertest/{username}")
	public User getUserByUsernamew(@PathVariable String username) {
		return authSvc.findUserByName(username);
	}

	@PutMapping("/register")
	public User register(@RequestBody User user, HttpServletResponse res) throws Exception {
		if (user == null) {
			res.sendError(HttpStatus.BAD_REQUEST.value());
			return null;
		}
		return authSvc.register(user);
	}

	@GetMapping("/auth")
	public User authenticate(Principal principal) {
		return authSvc.findUserByName(principal.getName());
	}
	
	@PutMapping("api/users")
	public User saveUser(@RequestBody User user, Principal principal, HttpServletResponse res) throws Exception {
		
		User managed = authSvc.findUserByName(principal.getName());
		if(managed == null) {
			res.sendError(HttpStatus.UNAUTHORIZED.value());
			return null;
		}
		managed.setFirstName(user.getFirstName());
		managed.setLastName(user.getLastName());
		managed.setEmail(user.getEmail());
		managed.setImageURL(user.getImageURL());
		userSvc.saveUser(managed);
		return managed;
	}

}
