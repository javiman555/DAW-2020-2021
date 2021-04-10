package com.trec.rest.controller;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trec.controller.DefaultModeAttributes;
import com.trec.model.Dish;
import com.trec.model.Ingredient;
import com.trec.service.DishService;
import com.trec.service.IngredientService;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController extends DefaultModeAttributes{

	@Autowired
	private DishService dishService;
	@Autowired
	private IngredientService ingredientService;

	@GetMapping("/")
	public ResponseEntity<List<Dish>> showDishes() {
		return ResponseEntity.ok(dishService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Dish> showDishById(@PathVariable long id) {

		Optional<Dish> dish = dishService.findById(id);
		
		if (dish.isPresent()) {
			return ResponseEntity.ok(dish.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Dish> removeDish(@PathVariable long id) {

		Optional<Dish> dish = dishService.findById(id);
		
		if (dish.isPresent()) {
			dishService.deleteById(id);
			return ResponseEntity.ok(dish.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/")
	public ResponseEntity<Dish> newDishProcess(@RequestBody Dish dish, @RequestParam List<String> lista, MultipartFile imageField) throws IOException {

		if (!imageField.isEmpty()) {
			dish.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			dish.setImage(true);
		}

		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		for(int i = 0; i < lista.size(); i++) {
		int idi = Integer.parseInt(lista.get(i));
		Ingredient ingredient=ingredientService.findById(idi).get();
		ingredients.add(ingredient);
		}
		
		dish.setIngredients(ingredients);
		
		dishService.save(dish);
		
		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(dish.getId()).toUri();

		return ResponseEntity.created(location).body(dish);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Dish> replaceDish(@RequestBody Dish dish, @RequestParam List<String> lista, boolean removeImage, MultipartFile imageField)
			throws IOException, SQLException {

		updateImage(dish, removeImage, imageField);
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		for(int i = 0; i < lista.size(); i++) {
			int idi = Integer.parseInt(lista.get(i));
			Ingredient ingredient=ingredientService.findById(idi).get();
			ingredients.add(ingredient);
		}

		dish.setIngredients(ingredients);
		
		dishService.save(dish);

		return ResponseEntity.ok(dish);
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws SQLException {

		Optional<Dish> dish = dishService.findById(id);
		if (dish.isPresent() && dish.get().getImageFile() != null) {

			Resource file = new InputStreamResource(dish.get().getImageFile().getBinaryStream());

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
					.contentLength(dish.get().getImageFile().length()).body(file);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	private void updateImage(Dish dish, boolean removeImage, MultipartFile imageField) throws IOException, SQLException {
		
		if (!imageField.isEmpty()) {
			dish.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			dish.setImage(true);
		} else {
			if (removeImage) {
				dish.setImageFile(null);
				dish.setImage(false);
			} else {
				// Maintain the same image loading it before updating the dish
				Dish dbDish = dishService.findById(dish.getId()).orElseThrow();
				if (dbDish.hasImage()) {
					dish.setImageFile(BlobProxy.generateProxy(dbDish.getImageFile().getBinaryStream(),
							dbDish.getImageFile().length()));
					dish.setImage(true);
				}
			}
		}
	}
}