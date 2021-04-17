package com.trec.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trec.model.Purchase;
import com.trec.service.PageableService;

@RestController
@RequestMapping("/notanapi/profile")
public class PurchasePaginationController {
	
	@Autowired
	private PageableService pageableService;
	
	@GetMapping("/{id}/purchases")
	public ResponseEntity<Page<Purchase>> showMore(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
		
		return pageableService.showUserMore(id, request, response);
	}
	
	@GetMapping("/purchases")
	public ResponseEntity<Page<Purchase>> showAdminMore(HttpServletRequest request, HttpServletResponse response) {
		
		return pageableService.showAdminMore(request, response);
	}
}
	