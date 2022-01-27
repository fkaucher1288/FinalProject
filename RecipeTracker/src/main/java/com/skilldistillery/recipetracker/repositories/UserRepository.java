package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	User findAllById(int id);
	
}
