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
import com.trec.model.Purchase;
import com.trec.model.User;
import com.trec.service.DishService;
import com.trec.repository.IngredientRepository;
import com.trec.repository.PurchaseRepository;
import com.trec.repository.UserRepository;


@Service
public class DatabaseInitializer {


	@Autowired
	private DishService dishService;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void init() throws IOException, URISyntaxException {

		// Sample dishes
		Ingredient pomelo =new Ingredient("Pomelo","gluten");
		ingredientRepository.save(pomelo);
		Ingredient banana =new Ingredient("Banana","fructosa");
		ingredientRepository.save(banana);
		Ingredient pan =new Ingredient("Pan","gluten");
		ingredientRepository.save(pan);
		Ingredient leche =new Ingredient("Leche","lactosa");
		ingredientRepository.save(leche);
		Ingredient harina =new Ingredient("Harina","gluten");
		ingredientRepository.save(harina);
		Ingredient huevo =new Ingredient("Huevo","huevo");
		ingredientRepository.save(huevo);
		Ingredient jamon =new Ingredient("Jamón","carne");
		ingredientRepository.save(jamon);
		Ingredient pasta =new Ingredient("Pasta","gluten");
		ingredientRepository.save(pasta);
		Ingredient tomate =new Ingredient("Tomate","");
		ingredientRepository.save(tomate);
		Ingredient carnePicada =new Ingredient("Carne picada","carne");
		ingredientRepository.save(carnePicada);
		Ingredient nata =new Ingredient("Nata","lactosa");
		ingredientRepository.save(nata);
		Ingredient bacon =new Ingredient("Bacon","carne");
		ingredientRepository.save(bacon);
		Ingredient queso =new Ingredient("Queso","lactosa");
		ingredientRepository.save(queso);
		Ingredient oregano =new Ingredient("Oregano","");
		ingredientRepository.save(oregano);
		
		List<Ingredient> ingredient= new ArrayList<Ingredient>();
		ingredient.add(pomelo);
		Dish dish1 = new Dish("Pomelo y nada mas", 2.00f, "Desayuno",ingredient);

		setDishImage(dish1, "/sample_images/pomelo.jpg");
		dishService.save(dish1);

		ingredient = new ArrayList<Ingredient>();
		ingredient.add(leche);
		ingredient.add(harina);
		ingredient.add(huevo);
		Dish dish2 = new Dish("Leche con galletas", 5.00f, "Desayuno",ingredient);
		
		setDishImage(dish2, "/sample_images/lecheConGalletas.jpg");
		dishService.save(dish2);
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(pan);
		ingredient.add(banana);
		Dish dish3 = new Dish("Pan de plátano", 8.00f, "Desayuno",ingredient);

		setDishImage(dish3, "/sample_images/pan_platano.jpg");
		dishService.save(dish3);

		ingredient = new ArrayList<Ingredient>();
		ingredient.add(pasta);
		ingredient.add(tomate);
		ingredient.add(carnePicada);
		Dish dish4 = new Dish("Pasta boloñesa", 15.00f, "Comida",ingredient);
		
		setDishImage(dish4, "/sample_images/pastaBoloñesa.jpg");
		dishService.save(dish4);
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(pasta);
		ingredient.add(nata);
		ingredient.add(bacon);
		Dish dish5 = new Dish("Pasta carbonara", 15.00f, "Comida",ingredient);

		setDishImage(dish5, "/sample_images/pastaCarbonara.jpg");
		dishService.save(dish5);
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(harina);
		ingredient.add(queso);
		ingredient.add(tomate);
		ingredient.add(oregano);
		Dish dish6 = new Dish("Pizza margarita", 15.00f, "Comida",ingredient);

		setDishImage(dish6, "/sample_images/pizzaMargarita.jpg");
		dishService.save(dish6);
		
		ingredient = new ArrayList<Ingredient>();
		ingredient.add(harina);
		ingredient.add(queso);
		ingredient.add(tomate);
		ingredient.add(oregano);
		ingredient.add(jamon);
		Dish dish7 = new Dish("Pizza jamón", 15.00f, "Cena",ingredient);
		
		setDishImage(dish7, "/sample_images/pizzaJamon.jpg");
		dishService.save(dish7);
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(harina);
		ingredient.add(queso);
		ingredient.add(tomate);
		ingredient.add(oregano);
		ingredient.add(bacon);
		Dish dish8 = new Dish("Pizza bacon", 15.00f, "Cena",ingredient);

		setDishImage(dish8, "/sample_images/pizzaBacon.jpg");
		dishService.save(dish8);
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(pan);
		ingredient.add(queso);
		ingredient.add(jamon);
		Dish dish9 = new Dish("Sándwich mixto", 10.00f, "Cena",ingredient);

		setDishImage(dish9, "/sample_images/sandwichMixto.jpg");
		dishService.save(dish9);
		
		// Sample users
		User user1 = new User("user1", passwordEncoder.encode("pass"), "USER");
		userRepository.save(new User("user", "Julia","Martín","juliamartin@gmail.com",222222222, passwordEncoder.encode("pass"), "USER"));
		userRepository.save(new User("admin","Pepe","Pérez","pepeperez@gmail.com",111111111, passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
		userRepository.save(new User("a","a","a","a@gmail.com",111111111, passwordEncoder.encode("a"), "USER"));
		
		List<Dish> dishes= new ArrayList<Dish>();
		dishes.add(dish5);
		dishes.add(dish6);
		dishes.add(dish7);
		// Sample purchases
		for (Dish dish : dishes) {
			dish.setNbuy(dish.getNbuy()+1);

			dish.setImageFile(null);
			dishService.save(dish);
			
		}
		
		Purchase purchase1 = new Purchase("Azahara", "Andújar", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, 20.5f, user1,dishes,24,2,2021);
		for (Dish dish : dishes) {
			dish.setNbuy(dish.getNbuy()+1);
			dish.setImageFile(null);
			dishService.save(dish);
		}
		
		Purchase purchase2 = new Purchase("Javier", "Méndez", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, 35.5f, user1,dishes,12,3,2021);
		
		userRepository.save(user1);
		
		purchaseRepository.save(purchase1);
		purchaseRepository.save(purchase2);
		
	}

	public void setDishImage(Dish dish, String classpathResource) throws IOException {
		dish.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		dish.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

}

