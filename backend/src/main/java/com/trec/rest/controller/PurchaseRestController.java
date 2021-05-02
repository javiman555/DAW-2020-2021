package com.trec.rest.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.service.PageableService;
import com.trec.service.PurchaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseRestController {
	
	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private PageableService pageableService;
	
	
	@GetMapping("/purchase/{id}") // Show a purchase
	public ResponseEntity<Purchase> findPurchaseById(@Parameter(description="id of purchase to be searched") @PathVariable long id) {

		Optional<Purchase> purchase = purchaseService.findById(id);
		
		if (purchase.isPresent()) {
			return ResponseEntity.ok(purchase.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Operation(summary = "User gets a page of its purchases")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Found the page of purchases",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid user id and page number supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be logged as a user to do this",
		content = @Content
		),
		 @ApiResponse(
		 responseCode = "404",
		 description = "Page not found",
		 content = @Content
		 )
		})
	
	@GetMapping("/{id}")
	public ResponseEntity<Purchase[]> showMore(@Parameter(description="id of user") @PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
		
		return pageableService.showArrayUserMore(id, request, response);
	}
	
	@Operation(summary = "Admin gets a page of all purchases")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Found the page of purchases",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		 responseCode = "400",
		 description = "Invalid page number supplied",
		 content = @Content
		 ), 
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		),
		 @ApiResponse(
		 responseCode = "404",
		 description = "Page not found",
		 content = @Content
		 )
		})
	
	@GetMapping("/")
	public ResponseEntity<Purchase[]> showAdminMore(HttpServletRequest request, HttpServletResponse response) {
		
		return pageableService.showArrayAdminMore(request, response);
	}
	
	// Show the fields of the purchases that will be shown in the graph
	
	@Operation(summary = "Get id of all purchases to show it on a graph")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Found all ids of the purchases",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		),
		 @ApiResponse(
		 responseCode = "404",
		 description = "Ids of purchases not found",
		 content = @Content
		 )
		})
	
	@GetMapping("/id")
	public ResponseEntity<List<Long>> findPurchasesId() {
		
		return ResponseEntity.ok(purchaseService.findIdAll());
		
	}
	
	@Operation(summary = "Get price of all purchases to show it on a graph")
	@ApiResponses(value = {
		 @ApiResponse(
		 responseCode = "200",
		 description = "Found all prices of the purchases",
		 content = {@Content(
		 mediaType = "application/json",
		 schema = @Schema(implementation=Dish.class)
		 )}
		 ),
		 @ApiResponse(
		responseCode = "403",
		description = "Forbidden. You have to be an admin to do this",
		content = @Content
		),
		 @ApiResponse(
		 responseCode = "404",
		 description = "Price of purchases not found",
		 content = @Content
		 )
		})
	
	@GetMapping("/price")
	public ResponseEntity<List<Float>> findPurchasesPrice() {
		
			return ResponseEntity.ok(purchaseService.findPriceAll());
		
	}
	
}