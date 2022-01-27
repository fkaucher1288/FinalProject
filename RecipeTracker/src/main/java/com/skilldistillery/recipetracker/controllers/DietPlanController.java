package com.skilldistillery.recipetracker.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.DietPlan;
import com.skilldistillery.recipetracker.entities.DietPlanIngredient;
import com.skilldistillery.recipetracker.entities.DietPlanRecipe;
import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.services.AuthService;
import com.skilldistillery.recipetracker.services.DietPlanService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class DietPlanController {
	
	@Autowired
	private DietPlanService dPlanSvc;
	
	@Autowired
	private AuthService authSvc;
	
	@GetMapping("dietplans")
	public List<DietPlan> index(){
		return dPlanSvc.index();
	}
	
	@GetMapping("dietplans/{dietPlanId}")
	public DietPlan showDietPlan(@PathVariable Integer dietPlanId, HttpServletResponse res) {
		DietPlan dietPlan = dPlanSvc.findDietPlanById(dietPlanId);
		if(dietPlan == null) {
			res.setStatus(404);
		}
		return dietPlan;
	}
	@GetMapping("dietplans/{dietPlanId}/ingredients")
	public List<DietPlanIngredient> getIngredients(@PathVariable int dietPlanId, HttpServletResponse res, Principal principal){
		DietPlan dPlan = dPlanSvc.findDietPlanById(dietPlanId);
		return dPlan.getIngredients();
	}
	@GetMapping("dietplans/{dietPlanId}/recipes")
	public List<DietPlanRecipe> getDietPlanRecipes(@PathVariable int dietPlanId, HttpServletResponse res, Principal principal){
		DietPlan dPlan = dPlanSvc.findDietPlanById(dietPlanId);
		return dPlan.getRecipes();
	}
	@PostMapping("dietplans")
	public DietPlan createDietPlan(@RequestBody DietPlan dietPlan, HttpServletResponse res, HttpServletRequest req, Principal principal){
		User user = authSvc.findUserByName(principal.getName());
		dietPlan.setUser(user);
		return 	dPlanSvc.createDietPlan(dietPlan);
	}
	
	@PutMapping("dietplans")
	public DietPlan updateDietPlan(@RequestBody DietPlan dietPlan, HttpServletResponse res, HttpServletRequest req, Principal principal) throws Exception{
		User user = authSvc.findUserByName(principal.getName());
		List<DietPlan> dietPlans = user.getDietPlans();
		for(DietPlan plan: dietPlans) {
			if(plan.equals(dietPlan)) {
				dietPlan.setUser(user);
				return dPlanSvc.updateDietPlan(dietPlan);
			}
		}
		res.sendError(HttpStatus.NOT_FOUND.value());
		return null;
	}
	
	@DeleteMapping("dietplans/{dietPlanId}")
	public void deleteDietPlan(@PathVariable Integer dietPlanId,  HttpServletResponse res ) {
		try {
			if(dPlanSvc.deleteDietPlan(dietPlanId)) {
				res.setStatus(204);
			}else {
				res.setStatus(404);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			
		}
	}
}
