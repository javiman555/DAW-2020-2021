package com.trec.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
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
			model.addAttribute("userName", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));

		} else {
			model.addAttribute("logged", false);
		}
	}
	/*
	@PostMapping("/adddish")
	public String addDish(Model model,HttpServletRequest request, Long id) {
		
		System.out.println(id);
		Dish dish = dishService.findById(id).get();
		Principal principal = request.getUserPrincipal();
		String name = principal.getName();
		User user = userService.findByName(name).get();
		user.getNewPurchase().getDishes().add(dish);
		userService.deleteById(user.getId());
		userService.save(user);
		return "/menu";
	}
*/
	
}
