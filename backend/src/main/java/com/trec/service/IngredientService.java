package com.trec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trec.model.Ingredient;
import com.trec.repository.IngredientRepository;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	public Optional<Ingredient> findById(long id) {
		return ingredientRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return ingredientRepository.existsById(id);
	}

	public List<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}

	public void save(Ingredient ingredient) {
		ingredientRepository.save(ingredient);
	}

	public void delete(long id) {
		ingredientRepository.deleteById(id);
	}
}
