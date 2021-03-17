package com.trec.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.trec.model.Dish;
import com.trec.model.Ingredient;
import com.trec.model.User;
import com.trec.service.DishService;
import com.trec.service.UserService;

@Controller
public class LoginWebController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
	@RequestMapping("/login")
	public String login(Model model) {
		return "index";
	}

	@RequestMapping("/loginerror")
	public String loginerror(Model model) {
		
		model.addAttribute("loginerror", true);
		
		return "register";
	}
	
	@RequestMapping("/logout")
	public String logout(Model model) {
		
		model.addAttribute("loginerror", false);
		
		return "register";
	}
	
	@PostMapping("/newuser")
	public String newUserProcess(Model model, User user) throws IOException {

	/*	if (!imageField.isEmpty()) {
			user.setImageFile(BlobProxy.generateProxy(imageField.getInputStream(), imageField.getSize()));
			user.setImage(true);
		}
*/
		user.setImage(false);
		
		user.setEncodedPassword(passwordEncoder.encode(user.getEncodedPassword()));
		userService.save(user);

		model.addAttribute("userId", user.getId());
		
		System.out.println(userService.findById(user.getId()).get().getFirstName());
		System.out.println(user.getFirstName());
		System.out.println(userService.findById(user.getId()).get().toString());

		return "redirect:/profile/"+user.getId();
	}

}
