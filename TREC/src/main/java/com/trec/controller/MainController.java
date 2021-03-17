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
