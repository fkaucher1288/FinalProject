package com.skilldistillery.recipetracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.DietPlan;
import com.skilldistillery.recipetracker.services.DietPlanService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class DietPlanController {
	
	@Autowired
	private DietPlanService dPlanSvc;
	
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
	
	@PostMapping("dietplans")
	public DietPlan createDietPlan(@RequestBody DietPlan dietPlan, HttpServletResponse res, HttpServletRequest req) {
		try {
			dPlanSvc.createDietPlan(dietPlan);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(dietPlan.getId());
			res.setHeader("Location", url.toString());
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("INVALID JSON FOR NEW DietPlan");
			res.setStatus(400);
			dietPlan = null;
		}
		return dietPlan;
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
