package com.trec.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.trec.model.Ingredient;
import com.trec.service.IngredientService;

import org.springframework.web.multipart.MultipartFile;

@Controller
public class IngredientController extends DefaultModeAttributes{

	@Autowired
	private IngredientService ingredientService;
	
	
	@GetMapping("/add_food")
	public String add_food(Model model) {
		model.addAttribute("ingredients", ingredientService.findAll());

		return "add_food";
	}
	@GetMapping("/removeingredient/{id}")
	public String removeIngredient(Model model, @PathVariable long id) {

		Optional<Ingredient> ingredient = ingredientService.findById(id);
		if (ingredient.isPresent()) {
			ingredientService.delete(id);
		}
		return "/add_food";
	}

	@GetMapping("/newingredient")
	public String newIngredient(Model model) {
		model.addAttribute("ingredients", ingredientService.findAll());
		return "/add_food";
	}

	@PostMapping("/newingredient")
	public String newIngredientProcess(Model model, Ingredient ingredient, MultipartFile imageField) throws IOException {

		ingredientService.save(ingredient);
		model.addAttribute("ingredients", ingredientService.findAll());

		return "/add_food";
	}

}
