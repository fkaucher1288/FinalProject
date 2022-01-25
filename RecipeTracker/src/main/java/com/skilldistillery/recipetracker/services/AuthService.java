package com.skilldistillery.recipetracker.services;

import com.skilldistillery.recipetracker.entities.User;

public interface AuthService {

	User register(User user);

	User findUserByName(String username);
	
}
