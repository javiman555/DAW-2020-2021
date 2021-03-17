package com.trec.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
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

	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userNamexx", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			
			User user = userService.findByName(principal.getName()).get();
			model.addAttribute("userId", user.getId());

		} else {
			model.addAttribute("logged", false);
		}
	}
	
	@PostMapping("/adddish")
	public String addDish(Model model, Long id, HttpServletRequest request) {
		
		
		Principal principal = request.getUserPrincipal();
		String userNamexx = principal.getName();
		
		System.out.println(id);
		System.out.println(userNamexx);
		Dish dish = dishService.findById(id).get();
		User user = userService.findByName(userNamexx).get();
		System.out.println(id);
		if(user.getNewPurchase()==null){
			user.setNewPurchase(new Purchase());
			user.getNewPurchase().setDishes(new ArrayList<Dish>() );
		}
		System.out.println(id);
		user.getNewPurchase().getDishes().add(dish);
		System.out.println(userService.findByName(userNamexx).get());
		System.out.println(userService.findByName(userNamexx).get().getNewPurchase());
		System.out.println(userService.findByName(userNamexx).get().getNewPurchase().getDishes().get(0));
		System.out.println(userService.findByName(userNamexx).get());
		System.out.println(id);
		userService.save(user);
		return "/menu";
	}
	@GetMapping("/profile")
	public String showPurchases(Model model,String userNamexx) {

		model.addAttribute("purchases", userService.findByName(userNamexx).get().getPurchases());


		return "profile";
	}
	
	@GetMapping("/cart")
	public String showPurchase(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userNamexx = principal.getName();
		Purchase newPurchase = new Purchase();
		if(userService.findByName(userNamexx).get().getNewPurchase()!=null) {
			newPurchase =userService.findByName(userNamexx).get().getNewPurchase();
		}
		
			model.addAttribute("dishes", newPurchase.getDishes());
			
		return "cart";
	}
		
	@GetMapping("/profile/{id}")
	public String showProfile(Model model, @PathVariable long id) {
		
		Optional<User> user = userService.findById(id);
		
		model.addAttribute("purchases", purchaseService.getByUser(user.get()));
		
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
			return "/profile";
		} else {
			return "/register";
		}
	}
	
	@GetMapping("/purchase/{id}")
	public String showPurchase(Model model, @PathVariable long id) {
		
		model.addAttribute("purchase", purchaseService.findById(id).get());
		
		return "purchase";
	}
}
