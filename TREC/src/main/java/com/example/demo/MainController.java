package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import TREC_Repositories.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/index")
	public String index(Model model) {

		return "index";
	}
	
	@GetMapping("/equipo")
	public String equipo(Model model) {

		return "equipo";
	}
	
	@GetMapping("/carta")
	public String carta(Model model) {

		return "carta";
	}
	
	@GetMapping("/contacto")
	public String contacto(Model model) {

		return "contacto";
	}
	
	@GetMapping("/registro")
	public String registro(Model model) {

		return "registro";
	}
	
	@GetMapping("/carro")
	public String carro(Model model) {

		return "carro";
	}
	
	@GetMapping("/pagar")
	public String pagar(Model model) {

		return "pagar";
	}
	
	@GetMapping("/perfil")
	public String perfil(Model model) {

		return "perfil";
	}
	
	@GetMapping("/add_food")
	public String add_food(Model model) {

		return "add_food";
	}
	
}
