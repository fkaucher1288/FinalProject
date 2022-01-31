package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.User;

public interface UserService {
	
	List<User> getAllUsers();
	
	User getUserById(int id);
	
	User saveUser(User user);
	
	double getAvgRating(int userId);
	

}
