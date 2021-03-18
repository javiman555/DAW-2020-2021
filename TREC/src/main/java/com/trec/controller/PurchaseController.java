package com.trec.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.trec.model.Dish;
import com.trec.model.User;
import com.trec.model.Purchase;
import com.trec.service.PurchaseService;
import com.trec.service.DishService;
import com.trec.service.UserService;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PurchaseController {

	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private DishService dishService;
	@Autowired
	private UserService userService;

	
	@PostMapping("/adddish")
	public String addDish(Model model, Long id, HttpServletRequest request) {
		
		
		Principal principal = request.getUserPrincipal();
		String userNamexx = principal.getName();
		
		Dish dish = dishService.findById(id).get();
		User user = userService.findByName(userNamexx).get();
		if(user.getNewPurchase()==null){
			user.setNewPurchase(new Purchase());
			user.getNewPurchase().setDishes(new ArrayList<Dish>() );
		}
		user.getNewPurchase().getDishes().add(dish);

		userService.save(user);
		purchaseService.save(user.getNewPurchase());
		
		model.addAttribute("dishes1", dishService.getByCategory("Desayuno"));
		model.addAttribute("dishes2", dishService.getByCategory("Comida"));
		model.addAttribute("dishes3", dishService.getByCategory("Cena"));
		
		return "menu";
	}
	
	@GetMapping("/cart")
	public String showPurchase(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userNamexx = principal.getName();
		Purchase newPurchase = new Purchase();
		User user = userService.findByName(userNamexx).get();
		if(user.getNewPurchase()!=null) {
			newPurchase =userService.findByName(userNamexx).get().getNewPurchase();
		}else {
		newPurchase.setUser(user);	
		user.setNewPurchase(newPurchase);
		userService.save(user);
		}
			model.addAttribute("dishes", newPurchase.getDishes());
			
		return "cart";
	}
	@GetMapping("/profile/{id}")
	public String showProfile(Model model, @PathVariable long id, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (userReal.get().getId() == user.get().getId()) {
			
			model.addAttribute("purchases", purchaseService.getByUser(user.get()));
			return "profile";
		}else {
			return "404";
		}
		
	}
	@GetMapping("/purchases")
	public Page<Purchase> getPurchases(@RequestParam(required = false) User user, Pageable page) {

		if (user.getRoles().contains("ADMIN")) {
			return purchaseService.findAll(page);
		} else {
			return purchaseService.getByUser(user, page);
		}
	}
	@GetMapping("/purchase/{id}")
	public String showPurchase(Model model, @PathVariable long id, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Purchase purchase =purchaseService.findById(id).get();
		
		if(userReal.get().getRoles().contains("ADMIN") || userReal.get().getId() == purchase.getUser().getId() ) {
			
			model.addAttribute("purchase",purchase);
		
			return "purchase";
		}else {
			return "404";
		}
	
	}
	@GetMapping("/pay")
	public String pagar(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		User userReal = userService.findByName(userNameReal).get();
		if (!userReal.getNewPurchase().getDishes().isEmpty()) {
			return "pay";
		}
		
		return "/menu";
		
	}
	@PostMapping("/processpay")
	public String processPay(Model model, Purchase purchase, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();

		User userReal = userService.findByName(userNameReal).get();
		Purchase newPurchase = userReal.getNewPurchase();

		if (purchase.getFirstName().equals(userReal.getFirstName()) && purchase.getSurname().equals(userReal.getSurname())) {
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
			
			purchaseService.save(newPurchase);
			userReal.setNewPurchase(null);
			userReal.getPurchases().add(newPurchase);
			userService.save(userReal);
			
		}else {
			return "/payerror";
		}
		
		return "/paydone";
	}
}
