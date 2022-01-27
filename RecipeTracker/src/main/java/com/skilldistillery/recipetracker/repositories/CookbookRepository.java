package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.Cookbook;

public interface CookbookRepository extends JpaRepository<Cookbook, Integer>  {

}
