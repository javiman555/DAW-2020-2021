package com.trec.rest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trec.model.Dish;
import com.trec.model.Ingredient;
import com.trec.service.DishService;
import com.trec.service.IngredientService;


@RestController
@RequestMapping("/api/ingredients")
public class IngredientRestController{

	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private DishService dishService;
	
	
	@GetMapping("/")//show all ingredients
	public ResponseEntity<List<Ingredient>> showIngredients() {
		return ResponseEntity.ok(ingredientService.findAll());
	}
	
	@GetMapping("/{id}") //show an ingredient
	public ResponseEntity<Ingredient> showIngredientById(@PathVariable long id) {

		Optional<Ingredient> ingredient = ingredientService.findById(id);
		
		if (ingredient.isPresent()) {
			return ResponseEntity.ok(ingredient.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")//change an ingredient
	public ResponseEntity<Ingredient> replaceIngredient(@PathVariable long id, @RequestBody Ingredient newIngredient) {

		Optional<Ingredient> ingredient = ingredientService.findById(id);

		if (ingredient != null) {

			newIngredient.setId(id);
			ingredientService.save(newIngredient);

			return ResponseEntity.ok(newIngredient);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")//create an ingredient
	public ResponseEntity<Ingredient> newIngredientProcess(@RequestBody Ingredient ingredient){

		ingredientService.save(ingredient);

		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(ingredient.getId()).toUri();

		return ResponseEntity.created(location).body(ingredient);
	}

}