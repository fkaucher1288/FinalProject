package com.skilldistillery.recipetracker.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.repositories.RecipeRatingRepository;
import com.skilldistillery.recipetracker.repositories.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RecipeRatingRepository ratingRepo;

	@Override
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
	}

	@Override
	public User saveUser(User user) {
		return	userRepo.saveAndFlush(user);
	
	}

	

	@Override
	public User getUserById(int id) {
		
		Optional<User> userOpt = userRepo.findById(id);
		User user = null;
		if(userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
		
	
	}

	@Override
	public double getAvgRating(int userId) {
		return ratingRepo.getAvgRatingByRecipe_User_Id(userId);
	}

	}

	


