package com.trec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trec.repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/index")
	public String index(Model model) {

		return "index";
	}
	
	@GetMapping("/team")
	public String equipo(Model model) {

		return "team";
	}
	
	@GetMapping("/contact")
	public String contacto(Model model) {

		return "contact";
	}
	
/*	@GetMapping("/register")
	public String login(Model model) {

		return "register";
	}
	*/
	@GetMapping("/register")
	public String showDishes(Model model) {

		model.addAttribute("loginerror", false);

		return "register";
	}
	
	
	@GetMapping("/pagar")
	public String pagar(Model model) {

		return "pagar";
	}
	
	
}
