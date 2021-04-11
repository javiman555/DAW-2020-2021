package com.trec.rest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
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

//import es.codeurjc.board.Post;

import com.trec.service.ImageService;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController extends DefaultModeAttributes{

	private static final String POSTS_FOLDER = "posts";
	
	@Autowired
	private DishService dishService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private ImageService imgService;

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

//	@PostMapping("/")
//	public ResponseEntity<Dish> createPost(@RequestBody Post post) {
//
//		posts.save(post);
//
//		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
//
//		return ResponseEntity.created(location).body(post);
//	}
	
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
	
	@PostMapping("/{id}/image")
	public ResponseEntity<Object> uploadImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {

		Dish dish = dishService.findById(id).get();

		if (dish != null) {

			URI location = fromCurrentRequest().build().toUri();

			dish.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
			dishService.save(dish);

			imgService.saveImage(POSTS_FOLDER, dish.getId(), imageFile);

			return ResponseEntity.created(location).build();

		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}/image")
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws MalformedURLException {
		
		return this.imgService.createResponseFromImage(POSTS_FOLDER, id);
	}
	
	@DeleteMapping("/{id}/image")
	public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

		Dish dish = dishService.findById(id).get();
		
		if(dish != null) {
			
			dish.setImageFile(null);
			dishService.save(dish);
			
			this.imgService.deleteImage(POSTS_FOLDER, id);
			
			return ResponseEntity.noContent().build();
			
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
}