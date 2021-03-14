package com.trec.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

		// Sample books
		
		Dish dish1 = new Dish("Cereales con leche", 5.00f, "Desayuno","Cena");

		setDishImage(dish1, "/sample_images/tus_zonas_erroneas.jpg");
		dishRepository.save(dish1);

		Dish dish2 = new Dish("Cereales con leche", 5.00f, "Desayuno","Cena");

		setDishImage(dish2, "/sample_images/la_vida_secreta_de_la_mente.jpg");
		dishRepository.save(dish2);

		dishRepository.save(new Dish("Cereales con leche", 5.00f, "Desayuno","Cena"));

		dishRepository.save(new Dish("Cereales con leche", 5.00f, "Desayuno","Cena"));

		dishRepository.save(new Dish("Cereales con leche", 5.00f, "Desayuno","Cena"));
		
		ingredientRepository.save(new Ingredient("Pomelo","gluten"));

		ingredientRepository.save(new Ingredient("pan","lactosa"));

		ingredientRepository.save(new Ingredient("leche","lactosa"));

		// Sample users

		userRepository.save(new User("user", passwordEncoder.encode("pass"), "USER"));
		userRepository.save(new User("admin", passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
	}

	public void setDishImage(Dish dish, String classpathResource) throws IOException {
		dish.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		dish.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

}

