package com.trec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController extends DefaultModeAttributes{
		
	@GetMapping("/index")
	public String index(Model model) {

		return "index";
	}
	
	@GetMapping("/team")
	public String equipo(Model model) {

		return "team";
	}
	
	@GetMapping("/paydone")
	public String paydone(Model model) {

		return "paydone";
	}
	
	@GetMapping("/contact")
	public String contacto(Model model) {

		return "contact";
	}
	
	@GetMapping("/register")
	public String showDishes(Model model) {

		model.addAttribute("loginerror", false);

		return "register";
	}
	
	
	
}
