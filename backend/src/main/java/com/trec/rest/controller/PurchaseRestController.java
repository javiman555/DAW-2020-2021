package com.trec.rest.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trec.model.Purchase;
import com.trec.model.User;
import com.trec.service.DishService;
import com.trec.service.PageableService;
import com.trec.service.PurchaseService;
import com.trec.service.UserService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseRestController {
	
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private UserService userService;
	@Autowired
	private DishService dishService;
	@Autowired
	private PageableService pageableService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Page<Purchase>> showMore(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
		
		return pageableService.showUserMore(id, request, response);
	}
	
	@GetMapping("/")
	public ResponseEntity<Page<Purchase>> showAdminMore(HttpServletRequest request, HttpServletResponse response) {
		
		return pageableService.showAdminMore(request, response);
	}
	
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
	
	// Show the fields of the purchases that will be shown in the graph
	
	@GetMapping("/id")
	public ResponseEntity<List<Long>> showPurchasesId() {
		
		return ResponseEntity.ok(purchaseService.findIdAll());

		
	}
	
	@GetMapping("/price")
	public ResponseEntity<List<Float>> showPurchasesPrice() {
		
			return ResponseEntity.ok(purchaseService.findPriceAll());
		
	}
	
}