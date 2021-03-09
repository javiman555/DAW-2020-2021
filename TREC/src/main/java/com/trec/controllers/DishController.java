package com.trec.controllers;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trec.repositories.DishRepository;
import com.trec.models.Dish;
import com.trec.services.DishService;

@RestController
@RequestMapping("/carta")
public class DishController {

	@Autowired
	private DishRepository dishRepository;
	private DishService dishService;
	
	@GetMapping("/")
	public String showPosts(Model model) {

		model.addAttribute("posts", dishRepository.findAll());

		return "index";
	}

	@GetMapping("/post/{id}")
	public String showPost(Model model, @PathVariable long id) {

		Dish dish = dishService.findById(id);

		model.addAttribute("post", dish);

		return "show_post";
	}


	@PostConstruct
	public void init() {
	}

}