package com.skilldistillery.recipetracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.DietPlan;
import com.skilldistillery.recipetracker.entities.DietPlanIngredient;
import com.skilldistillery.recipetracker.entities.DietPlanRecipe;
import com.skilldistillery.recipetracker.repositories.DietPlanRepository;

@Service
public class DietPlanServiceImpl implements DietPlanService {

	@Autowired
	private DietPlanRepository dPlanRepo;
	
	@Override
	public List<DietPlan> index() {
		
		return dPlanRepo.findAll();
	}

	@Override
	public DietPlan findDietPlanById(int dietPlanId) {
		Optional<DietPlan> op = dPlanRepo.findById(dietPlanId);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public DietPlan createDietPlan(DietPlan dietPlan) {
		
		return dPlanRepo.saveAndFlush(dietPlan);
	}

	@Override
	public DietPlan updateDietPlan(DietPlan dietPlan) {
		if(dPlanRepo.existsById(dietPlan.getId())) {
			return dPlanRepo.save(dietPlan);
		}
		return null;
	}

	@Override
	public boolean deleteDietPlan(int dietPlanId) {
		boolean deleted = false;
		dPlanRepo.deleteById(dietPlanId);
		if(dPlanRepo.findById(dietPlanId) == null) {
			deleted = true;
		}
		return deleted;
	}


}
