package com.skilldistillery.recipetracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.Cookbook;
import com.skilldistillery.recipetracker.repositories.CookbookRepository;

@Service
public class CookbookServiceImpl implements CookbookService {


	@Autowired
	private CookbookRepository cookRepo;
	
	@Override
	public List<Cookbook> index() {
		
		return cookRepo.findAll();
	}

	@Override
	public Cookbook findCookbookById(int cookbookId) {
		Optional<Cookbook> op = cookRepo.findById(cookbookId);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public Cookbook createCookbook(Cookbook cookbook) {
		
		return cookRepo.saveAndFlush(cookbook);
	}

	@Override
	public Cookbook updateCookbook(int cookbookId, Cookbook cookbook) {
		if(cookRepo.existsById(cookbookId)) {
			return cookRepo.save(cookbook);
		}
		return null;
	}

	@Override
	public boolean deleteCookbook(int cookbookId) {
		boolean deleted = false;
		cookRepo.deleteById(cookbookId);
		if(cookRepo.findById(cookbookId) == null) {
			deleted = true;
		}
		return deleted;
	}
}