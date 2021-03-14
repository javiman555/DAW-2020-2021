package com.trec.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginWebController {
	
	@RequestMapping("/login")
	public String login() {
		return "register";
	}

	@RequestMapping("/loginerror")
	public String loginerror(Model model) {
		
		model.addAttribute("loginerror", true);
		
		return "register";
	}
}
