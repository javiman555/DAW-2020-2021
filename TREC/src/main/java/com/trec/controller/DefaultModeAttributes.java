package com.trec.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.trec.model.User;
import com.trec.service.UserService;

@ControllerAdvice

public class DefaultModeAttributes {
	
	@Autowired
	UserService userService;
	
	@ModelAttribute
	public void addAttributes(Model model, HttpServletRequest request) {

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			model.addAttribute("logged", true);
			model.addAttribute("userNamexx", principal.getName());
			model.addAttribute("admin", request.isUserInRole("ADMIN"));
			
			try {
				
				User user = userService.findByName(principal.getName()).get();
				model.addAttribute("userId", user.getId());
				
		}catch (RuntimeException e) {
			model.addAttribute("logged", false);
		    }

		} else {
			model.addAttribute("logged", false);
		}
	}
}