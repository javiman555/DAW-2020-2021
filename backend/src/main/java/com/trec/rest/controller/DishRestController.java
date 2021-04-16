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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

	@Operation(summary = "Get all dishes")
	@ApiResponses(value = {
			 @ApiResponse(
			 responseCode = "200",
			 description = "Found all dishes",
			 content = {@Content(
			 mediaType = "application/json",
			 schema = @Schema(implementation=Dish.class)
			 )}
			 ),
			 @ApiResponse(
			 responseCode = "404",
			 description = "Dishes not found",
			 content = @Content
			 )
			})

	@GetMapping("/") // Show all dishes
	public ResponseEntity<List<Dish>> findDishes() {
		return ResponseEntity.ok(dishService.findAll());
	}
	
	@Operation(summary = "Get a dish by its id")
		@ApiResponses(value = {
			 @ApiResponse(
			 responseCode = "200",
			 description = "Found the dish",
			 content = {@Content(
			 mediaType = "application/json",
			 schema = @Schema(implementation=Dish.class)
			 )}
			 ),
			 @ApiResponse(
			 responseCode = "400",
			 description = "Invalid id supplied",
			 content = @Content
			 ), 
			 @ApiResponse(
			 responseCode = "404",
			 description = "Dish not found",
			 content = @Content
			 )
			})
	
	@GetMapping("/{id}") // Show a dish
	public ResponseEntity<Dish> findDishById(@Parameter(description="id of dish to be searched") @PathVariable long id) {

		Optional<Dish> dish = dishService.findById(id);
		
		if (dish.isPresent()) {
			return ResponseEntity.ok(dish.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Get all dishes from a category")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Found the dishes from a category",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid category supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Dishes from category not found",
		 content = @Content
		 )
		})
	
	@GetMapping("/category") // "/type?category=Desayuno"
	public ResponseEntity<List<Dish>> findDishesByTipe(@Parameter(description="category of dish to be searched (desayuno, comida or cena)") @RequestParam String category) {
		return ResponseEntity.ok(dishService.getByCategory(category));
	}
	
	@Operation(summary = "Delete a dish by its id (only admin can do that)")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "The dish has been deleted correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid id supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Dish couldn't be deleted",
		 content = @Content
		 ),		 
		})
	
	@DeleteMapping("/{id}") //Delete Dish form database and all purchases
	public ResponseEntity<Dish> removeDish(@Parameter(description="id of the dish to be deleted") @PathVariable long id) {

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
			dish.get().setIngredients(null);
			dishService.deleteById(id);
			return ResponseEntity.ok(dish.get());
			
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Upload a new dish without an image (only admin can do that)")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "201",
		 description = "The dish has been uploaded correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid form of introducing the data for the new dish",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Dish couldn't be created",
		 content = @Content
		 )
		})
	
	@PostMapping("/")//Create Dish without image
	public ResponseEntity<Dish> newDishProcess(@Parameter(description="to create a new dish: name, price, category and list of ingredients in JSON") @RequestBody Dish dish)  {

		dish.setImage(false);
		
		dishService.save(dish);
		
		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(dish.getId()).toUri();

		return ResponseEntity.created(location).body(dish);
	}

	@Operation(summary = "Update a dish not including the image (only admin can do that)")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "The dish has been updated correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid form of introducing the data for updating the dish",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Dish couldn't be updated",
		 content = @Content
		 )
		})
	
	@PutMapping("/{id}")//Change some fields from dish (not image)
	public ResponseEntity<Dish> replaceDish(@Parameter(description="id of dish to be updated")@PathVariable long id, @Parameter(description="information of the dish you need to change")@RequestBody Dish dish)
			throws IOException, SQLException {
		
		if (dish != null) {
			
			Dish oldish = dishService.findById(id).get();
			
			Dish newdish = dishService.updateDish(oldish,dish);
			
			dishService.save(newdish);

			return ResponseEntity.ok(newdish);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "Upload the image of a dish (only admin can do that)")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "The dish image has been uploaded correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid form of introducing the image of the dish",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Dish image couldn't be uploaded",
		 content = @Content
		 )
		})
	
	@PostMapping("/{id}/image")//Change image of dish
	public ResponseEntity<Object> uploadDishImage(@Parameter(description="id of dish") @PathVariable long id,@Parameter(description="image of the dish") @RequestParam MultipartFile imageFile) throws IOException {

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
	
	@Operation(summary = "Download the image of a dish")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "The dish image has been downloaded correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "404",
		 description = "Dish image couldn't be downloaded",
		 content = @Content
		 )
		})
	
	@GetMapping("/{id}/image")//show image of dish
	public ResponseEntity<Object> downloadImage(@Parameter(description="id of dish") @PathVariable long id) throws MalformedURLException {
		
		return this.imgService.createResponseFromImage(POSTS_FOLDER, id);
	}
	
	@Operation(summary = "Delete the image of a dish (only admin can do that)")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "204",
		 description = "No content. The dish image has been deleted correctly",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		responseCode = "400",
	    description = "Invalid id supplied",
		content = @Content
		), 
		@ApiResponse(
	    responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		), 
		 @ApiResponse(
		 responseCode = "404",
		 description = "Dish image couldn't be deleted",
		 content = @Content
		 )
		})
	
	@DeleteMapping("/{id}/image")//delete image of dish
	public ResponseEntity<Object> deleteImage(@Parameter(description="id of dish") @PathVariable long id) throws IOException {

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