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

import com.trec.model.Ingredient;
import com.trec.service.IngredientService;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

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
	
	@GetMapping("/add_food")
	public String add_food(Model model) {
		model.addAttribute("ingredients", ingredientService.findAll());

		return "add_food";
	}
	@GetMapping("/removeingredient-{id}")
	public String removeIngredient(Model model, @PathVariable long id) {

		Optional<Ingredient> ingredient = ingredientService.findById(id);
		if (ingredient.isPresent()) {
			ingredientService.delete(id);
		}
		return "/add_food";
	}

	@GetMapping("/newingredient")
	public String newIngredient(Model model) {
		model.addAttribute("ingredients", ingredientService.findAll());
		return "/add_food";
	}

	@PostMapping("/newingredient")
	public String newIngredientProcess(Model model, Ingredient ingredient, MultipartFile imageField) throws IOException {

		ingredientService.save(ingredient);
		model.addAttribute("ingredients", ingredientService.findAll());

		return "/add_food";
	}

}
