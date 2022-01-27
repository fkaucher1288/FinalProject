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

import com.skilldistillery.recipetracker.entities.Cookbook;
import com.skilldistillery.recipetracker.services.CookbookService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class CookbookController {
	
	@Autowired
	private CookbookService cookSvc;
	
	@GetMapping(path="cookbook/{cookbookId}")
	public Cookbook getCookbook(@PathVariable Integer cookbookId, HttpServletResponse rsp) {
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
	
	
	@PostMapping("cookbook")
	public Cookbook createCookbook(@RequestBody Cookbook cookbook, HttpServletResponse res, HttpServletRequest req) {
		try {
			cookSvc.createCookbook(cookbook);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(cookbook.getId());
			res.setHeader("Location", url.toString());
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("INVALID JSON FOR NEW DietPlan");
			res.setStatus(400);
			cookbook = null;
		}
		return cookbook;
	}
	
	@DeleteMapping("cookbook/{cookbookId}")
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

