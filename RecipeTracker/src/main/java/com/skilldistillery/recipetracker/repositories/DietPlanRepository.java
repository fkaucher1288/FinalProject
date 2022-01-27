package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.DietPlan;

public interface DietPlanRepository extends JpaRepository<DietPlan, Integer>{

}
