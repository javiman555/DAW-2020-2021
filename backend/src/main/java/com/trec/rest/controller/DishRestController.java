package com.trec.rest.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
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
import com.trec.model.Purchase;
import com.trec.service.DishService;
import com.trec.service.PurchaseService;

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
	private PurchaseService purchaseService;
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
			List<Purchase> purchases = purchaseService.findAll();
			List<Dish> dishes = null;
			Dish dishon = null;
			int dishIs;
			for (Purchase purchase : purchases) {
				dishIs = 0;
				dishes = purchase.getDishes();
				for (Dish d : dishes) {
					
					if (d.getId().equals(id)) {
						dishon = d;
						dishIs = dishIs+1;
					}	
				}
				
				if (dishIs > 0) {
					while(dishIs > 0) {
						dishes.remove(dishon);
						dishIs = dishIs -1;
					}
					
					purchase.setDishes(dishes);
					purchaseService.save(purchase);
				}
			}
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
	public ResponseEntity<Dish> newDishProcess(@RequestBody Dish dish)  {

		dish.setImage(false);
		
		dishService.save(dish);
		
		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(dish.getId()).toUri();

		return ResponseEntity.created(location).body(dish);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Dish> replaceDish(@RequestBody Dish dish)
			throws IOException, SQLException {
		
		dishService.save(dish);

		return ResponseEntity.ok(dish);
	}
	
	@PostMapping("/{id}/image")
	public ResponseEntity<Object> uploadDishImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {

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