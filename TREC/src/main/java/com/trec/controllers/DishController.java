package com.trec.controllers;


import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trec.repositories.DishRepository;
import com.trec.models.Dish;
import com.trec.services.DishService;


@Controller
public class DishController {

	@Autowired
	private DishService dishService;
	

	@GetMapping("/carta")
	public String showDishes(Model model, HttpSession session) {

		model.addAttribute("dishes", dishService.findAll());
		model.addAttribute("welcome", session.isNew());

		return "carta";
	}

	@GetMapping("/dish/new")
	public String newDishForm(Model model) {


		return "new_dish";
	}
	
	@PostMapping("/dish/new")
	public String newDish(Model model, Dish dish) {

		dishService.save(dish);
	

		return "saved_dish";
	}
	@GetMapping("/dish/{id}")
	public String showDish(Model model, @PathVariable long id) {

		Optional<Dish> dish = dishService.findById(id);
		if (dish.isPresent()) {
			model.addAttribute("dish", dish.get());
			return "show_dish";
		} else {
			return "/carta";
		}

	}
	/*
	@GetMapping("/dish/{id}")
	public String showDish(Model model, @PathVariable long id) {

		Dish dish = dishService.findById(id);

		model.addAttribute("dish", dish);

		return "show_dish";
	}
	*/
	
	@GetMapping("/dish/{id}/delete")
	public String deleteDish(Model model, @PathVariable long id) {

		dishService.delete(id);

		return "deleted_dish";
	}
}