package com.skilldistillery.recipetracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {
		// encode password
		user.setPassword(encoder.encode(user.getPassword()));
		user.setEnabled(true);
		user.setRole("standard");
		return userRepo.saveAndFlush(user);
	}
	
	@Override
	public User findUserByName(String username) {
		return userRepo.findByUsername(username);
	}

}
