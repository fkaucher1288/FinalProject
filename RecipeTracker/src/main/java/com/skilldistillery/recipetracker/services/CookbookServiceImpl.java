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
	private CookbookRepository repo;
	
	@Override
	public Cookbook findById(int id) {
		Optional<Cookbook> op = repo.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public List<Cookbook> showAllCookbooks() {
		return repo.findAll();
	}

}