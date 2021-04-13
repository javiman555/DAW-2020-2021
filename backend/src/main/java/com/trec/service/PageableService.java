package com.trec.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.model.User;
import com.trec.repository.PurchaseRepository;

@Service
public class PageableService {

	@Autowired
	private UserService userService;
	@Autowired
	private PurchaseService purchaseService;

	public ResponseEntity<Page<Purchase>> showUserMore(long id, HttpServletRequest request, HttpServletResponse response) {
		
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
}
