package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {

}