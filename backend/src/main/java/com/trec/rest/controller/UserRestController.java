package com.trec.rest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.security.Principal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
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
import org.springframework.web.multipart.MultipartFile;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.model.User;
import com.trec.service.DishService;
import com.trec.service.ImageService;
import com.trec.service.PurchaseService;
import com.trec.service.UserService;;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private static final String POSTS_FOLDER = "posts";
	
	@Autowired
	private UserService userService;
	@Autowired
	private DishService dishService;
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private ImageService imgService;

	@GetMapping("/me")
	public ResponseEntity<User> me(HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null) {
			return ResponseEntity.ok(userService.findByName(principal.getName()).orElseThrow());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/") // Show all users
	public ResponseEntity<List<User>> showUsers() {
		return ResponseEntity.ok(userService.findAll());
	}
	@GetMapping("/{id}") // Show a user
	public ResponseEntity<User> showUserById(@PathVariable long id) {

		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}/dishes")// Show recomended dishes of the user
	public ResponseEntity<List<Dish>> showRecomendedDishes(@PathVariable long id) {
		return ResponseEntity.ok(dishService.getRecomended(id));
	}
	@GetMapping("/{id}/purchases") // Show all user purchases
	public ResponseEntity<List<Purchase>> showDishes(@PathVariable long id) {
		return ResponseEntity.ok(purchaseService.findAll());
	}
	@GetMapping("/{id}/newPurchase")// Show the current order of the user
	public ResponseEntity<Purchase> showNewPurchase(@PathVariable long id) {
		
		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			Purchase newpurchase = user.get().getNewPurchase();
			if(newpurchase != null) {
				return ResponseEntity.ok(newpurchase);
			}else {
				return ResponseEntity.notFound().build();
			}
			
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@PostMapping("/{id}/newPurchase")//Create an empty newPurchase of the user
	public ResponseEntity<Purchase> newPurchaseProcess(@PathVariable long id)  {

		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			Purchase newPurchase = new Purchase();
			user.get().setNewPurchase(newPurchase);
			userService.save(user.get());
			URI location = fromCurrentRequest().path("").build().toUri();
			return ResponseEntity.created(location).body(newPurchase);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PutMapping("/{id}/newPurchase")//Move newPurchase of the user the the purchase list
	public ResponseEntity<Purchase> newPurchaseDone(@PathVariable long id,@RequestBody Purchase dataPurchase)  {

		Optional<User> user = userService.findById(id);
		
		if (user.isPresent()) {
			Purchase newPurchase = user.get().getNewPurchase();
			dataPurchase.setId(newPurchase.getId());
			dataPurchase.setDishes(newPurchase.getDishes());
			user.get().setNewPurchase(null);
			user.get().getPurchases().add(dataPurchase);
			Calendar c = Calendar.getInstance();
			dataPurchase.setDateDay(c.get(Calendar.DATE));
			dataPurchase.setDateMonth(c.get(Calendar.MONTH));
			dataPurchase.setDateYear(c.get(Calendar.YEAR));
			dataPurchase.setUser(user.get());
			
			for (Dish dish : dataPurchase.getDishes()) {
				dish.setNbuy(dish.getNbuy()+1);
				dataPurchase.setPrice(dataPurchase.getPrice()+dish.getDishPrice());
				dishService.save(dish);
			}
			userService.save(user.get());
			
			return ResponseEntity.ok(dataPurchase);
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PutMapping("/{iduser}/newPurchase/dish/{iddish}")//Add a dish to the newPurchase of the user
	public ResponseEntity<Purchase> addDish(@PathVariable long iduser,@PathVariable long iddish)
			throws IOException, SQLException {
		Optional<User> user = userService.findById(iduser);
		
		if (user.isPresent()) {
			Optional<Dish> dish = dishService.findById(iddish);
			if (dish.isPresent()) {
				
				Purchase newPurchase = user.get().getNewPurchase();
				
				newPurchase.getDishes().add(dish.get());
				
				userService.save(user.get());

				return ResponseEntity.ok(newPurchase);

			} else {
				return ResponseEntity.notFound().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	
	
	@PostMapping("/")//Create User without image
	public ResponseEntity<User> newUserProcess(@RequestBody User user)  {

		user.setImage(false);
		
		userService.save(user);
		
		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(location).body(user);
	}

	@PutMapping("/{id}")//Change some fields from user (not image)
	public ResponseEntity<User> replaceUser(@PathVariable long id,@RequestBody User user)
			throws IOException, SQLException {
		
		if (user != null) {
			
			User olduser = userService.findById(id).get();
			
			User newuser = userService.updateUser(olduser,user);
			
			userService.save(newuser);

			return ResponseEntity.ok(newuser);

		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	@PostMapping("/{id}/image")//Change image of user
	public ResponseEntity<Object> uploadUserImage(@PathVariable long id, @RequestParam MultipartFile imageFile) throws IOException {

		User user = userService.findById(id).get();

		if (user != null) {

			URI location = fromCurrentRequest().build().toUri();

			user.setImageFile(BlobProxy.generateProxy(imageFile.getInputStream(), imageFile.getSize()));
			userService.save(user);

			imgService.saveImage(POSTS_FOLDER, user.getId(), imageFile);

			return ResponseEntity.created(location).build();

		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}/image")//show image of dish
	public ResponseEntity<Object> downloadImage(@PathVariable long id) throws MalformedURLException {
		
		return this.imgService.createResponseFromImage(POSTS_FOLDER, id);
	}
	
	@DeleteMapping("/{id}/image")//delete image of dish
	public ResponseEntity<Object> deleteImage(@PathVariable long id) throws IOException {

		User user = userService.findById(id).get();
		
		if(user != null) {
			
			user.setImageFile(null);
			userService.save(user);
			
			this.imgService.deleteImage(POSTS_FOLDER, id);
			
			return ResponseEntity.noContent().build();
			
		} else {
			return ResponseEntity.notFound().build();
		}		
	}
	
}
