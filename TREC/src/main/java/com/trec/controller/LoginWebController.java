package com.trec.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/login")
	public String login() {
		return "register";
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
		
		userService.save(user);

		model.addAttribute("userId", user.getId());
		
		System.out.print(userService.findById(user.getId()).get().getFirstName());

		return "/";
		//return "redirect:/profile/"+user.getId();
	}

}
