package com.trec.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.trec.model.Dish;
import com.trec.model.Ingredient;
import com.trec.model.User;
import com.trec.repository.DishRepository;
import com.trec.repository.IngredientRepository;
import com.trec.repository.UserRepository;


@Service
public class DatabaseInitializer {


	@Autowired
	private DishRepository dishRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() throws IOException, URISyntaxException {

		// Sample dishes
		Ingredient pomelo =new Ingredient("Pomelo","gluten");
		ingredientRepository.save(pomelo);
		Ingredient banana =new Ingredient("Banana","");
		ingredientRepository.save(banana);
		Ingredient pan =new Ingredient("Pan","gluten");
		ingredientRepository.save(pan);
		
		List<Ingredient> ingredient1= new ArrayList<Ingredient>();
		ingredient1.add(pomelo);
		Dish dish1 = new Dish("Pomelo y nada mas", 5.00f, "Desayuno",ingredient1);

		setDishImage(dish1, "/sample_images/pomelo.jpg");
		dishRepository.save(dish1);

		List<Ingredient> ingredient2= new ArrayList<Ingredient>();
		ingredient2.add(banana);
		ingredient2.add(pan);
		Dish dish2 = new Dish("Banana con Pan", 15.00f, "Cena",ingredient2);

		setDishImage(dish2, "/sample_images/pan_platano.jpg");
		dishRepository.save(dish2);


		// Sample users

		userRepository.save(new User("user", "Julia","Martín","juliamartin@gmail.com",222222222, passwordEncoder.encode("pass"), "USER"));
		userRepository.save(new User("admin","Pepe","Pérez","pepeperez@gmail.com",111111111, passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
	}

	public void setDishImage(Dish dish, String classpathResource) throws IOException {
		dish.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		dish.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

}

