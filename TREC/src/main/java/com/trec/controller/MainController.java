package com.trec.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.trec.model.User;
import com.trec.service.UserService;

@Controller
public class MainController {
	
	

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
	
	
	
}
