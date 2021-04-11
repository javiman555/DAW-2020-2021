package com.trec.rest.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.security.Principal;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.model.User;
import com.trec.service.DishService;
import com.trec.service.PurchaseService;
import com.trec.service.UserService;

@RestController
@RequestMapping("/api/profile")
public class PurchaseRestController {
	
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private UserService userService;
	@Autowired
	private DishService dishService;
	
	@GetMapping("/{id}/purchases")
	public ResponseEntity<Page<Purchase>> showMore(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		int pageRequested = 0;
		String numPage = request.getParameter("numPage");
		if (numPage != null) {
			pageRequested = Integer.parseInt(numPage);
		}
	
		if (userReal.get().getId() == user.get().getId()) {
			
			Page<Purchase> page = purchaseService.getByUser(user.get(), pageRequested);
			return ResponseEntity.ok(page);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/purchases")
	public ResponseEntity<Page<Purchase>> showAdminMore(HttpServletRequest request, HttpServletResponse response) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		
		int pageRequested = 0;
		String numPage = request.getParameter("numPage");
		if (numPage != null) {
			pageRequested = Integer.parseInt(numPage);
		}
		
		if (userReal.get().getRoles().contains("ADMIN")) {
			
			Page<Purchase> page = purchaseService.findAll(pageRequested);
			return ResponseEntity.ok(page);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Purchase> processPay(@RequestBody Purchase purchase, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();

		User userReal = userService.findByName(userNameReal).get();
		Purchase newPurchase = userReal.getNewPurchase();

		newPurchase.setFirstName(purchase.getFirstName());
		newPurchase.setSurname(purchase.getSurname());
		newPurchase.setAddress(purchase.getAddress());
		newPurchase.setPostalCode(purchase.getPostalCode());
		newPurchase.setCity(purchase.getCity());
		newPurchase.setCountry(purchase.getCountry());
		newPurchase.setPhoneNumber(purchase.getPhoneNumber());
		Calendar c = Calendar.getInstance();
		newPurchase.setDateDay(c.get(Calendar.DATE));
		newPurchase.setDateMonth(c.get(Calendar.MONTH));
		newPurchase.setDateYear(c.get(Calendar.YEAR));
		newPurchase.setUser(userReal);
		
		for (Dish dish : newPurchase.getDishes()) {
			dish.setNbuy(dish.getNbuy()+1);
			dishService.save(dish);
		}
		
		purchaseService.save(newPurchase);
		userReal.setNewPurchase(null);
		userReal.getPurchases().add(newPurchase);
		userService.save(userReal);
		
		URI location = fromCurrentRequest().path("/{id}")
				.buildAndExpand(purchase.getId()).toUri();

		return ResponseEntity.created(location).body(purchase);
	}
	 // PREGUNTAR POR ESTE Y POR PUT
	@DeleteMapping("/{id}")
	public ResponseEntity<Purchase> removePurchase(@PathVariable long id) {

		Optional<Purchase> purchase = purchaseService.findById(id);
		
		if (purchase.isPresent()) {
			dishService.deleteById(id);
			return ResponseEntity.ok(purchase.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}