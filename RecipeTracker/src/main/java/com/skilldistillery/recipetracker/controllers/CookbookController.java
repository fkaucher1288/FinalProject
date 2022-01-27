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

import com.skilldistillery.recipetracker.entities.Cookbook;
import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.services.AuthService;
import com.skilldistillery.recipetracker.services.CookbookService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class CookbookController {
	
	@Autowired
	private CookbookService cookSvc;
	
	@Autowired
	private AuthService authSvc;
	
	@GetMapping(path="cookbook/{cookbookId}")
	public Cookbook showCookbook(@PathVariable Integer cookbookId, HttpServletResponse rsp) {
		Cookbook cookbook = cookSvc.findCookbookById(cookbookId);
		if (cookbook == null) {
			rsp.setStatus(404);
		} else {
			rsp.setStatus(201);
		}
		return cookbook;
	}
	
	@GetMapping(path="cookbook")
	public List<Cookbook> index() {
		return cookSvc.index();
	}
	
	
	@PostMapping(path="cookbook")
	public Cookbook createCookbook(@RequestBody Cookbook cookbook, HttpServletResponse res, HttpServletRequest req, Principal principal) {
		User user = authSvc.findUserByName(principal.getName());
		cookbook.setUser(user);
		return 	cookSvc.createCookbook(cookbook);
	}	
	
	@PutMapping(path="cookbook")
	public Cookbook updateCookbook(@RequestBody Cookbook cookbook, HttpServletResponse res, HttpServletRequest req, Principal principal) throws Exception{
		User user = authSvc.findUserByName(principal.getName());
		List<Cookbook> cookbooks = user.getCookbooks();
		for(Cookbook plan: cookbooks) {
			if(plan.equals(cookbook)) {
				cookbook.setUser(user);
				return cookSvc.updateCookbook(cookbook);
			}
		}
		res.sendError(HttpStatus.NOT_FOUND.value());
		return null;
	}
	
	@DeleteMapping(path="cookbook/{cookbookId}")
	public void deleteCookbook(@PathVariable Integer cookbookId,  HttpServletResponse res ) {
		try {
			if(cookSvc.deleteCookbook(cookbookId)) {
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

