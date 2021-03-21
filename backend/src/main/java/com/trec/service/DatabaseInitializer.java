package com.trec.service;

import java.io.IOException;
import java.net.URISyntaxException;
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
		Ingredient pomelo =new Ingredient("Pomelo","");
		ingredientRepository.save(pomelo);
		Ingredient banana =new Ingredient("Banana","");
		ingredientRepository.save(banana);
		Ingredient pan =new Ingredient("Pan","gluten");
		ingredientRepository.save(pan);
		Ingredient leche =new Ingredient("Leche","lactosa");
		ingredientRepository.save(leche);
		Ingredient harina =new Ingredient("Harina","gluten");
		ingredientRepository.save(harina);
		Ingredient huevo =new Ingredient("Huevo","");
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

		ingredient = new ArrayList<Ingredient>();
		ingredient.add(leche);
		ingredient.add(harina);
		ingredient.add(huevo);
		Dish dish2 = new Dish("Leche con galletas", 5.00f, "Desayuno",ingredient);
		
		setDishImage(dish2, "/sample_images/lecheConGalletas.jpg");
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(pan);
		ingredient.add(banana);
		Dish dish3 = new Dish("Pan de plátano", 8.00f, "Desayuno",ingredient);

		setDishImage(dish3, "/sample_images/pan_platano.jpg");

		ingredient = new ArrayList<Ingredient>();
		ingredient.add(pasta);
		ingredient.add(tomate);
		ingredient.add(carnePicada);
		Dish dish4 = new Dish("Pasta boloñesa", 15.00f, "Comida",ingredient);
		
		setDishImage(dish4, "/sample_images/pastaBoloñesa.jpg");
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(pasta);
		ingredient.add(nata);
		ingredient.add(bacon);
		Dish dish5 = new Dish("Pasta carbonara", 15.00f, "Comida",ingredient);
		
		setDishImage(dish5, "/sample_images/pastaCarbonara.jpg");
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(harina);
		ingredient.add(queso);
		ingredient.add(tomate);
		ingredient.add(oregano);
		Dish dish6 = new Dish("Pizza margarita", 16.00f, "Comida",ingredient);

		setDishImage(dish6, "/sample_images/pizzaMargarita.jpg");
		
		ingredient = new ArrayList<Ingredient>();
		ingredient.add(harina);
		ingredient.add(queso);
		ingredient.add(tomate);
		ingredient.add(oregano);
		ingredient.add(jamon);
		Dish dish7 = new Dish("Pizza jamón", 13.00f, "Cena",ingredient);

		setDishImage(dish7, "/sample_images/pizzaJamon.jpg");
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(harina);
		ingredient.add(queso);
		ingredient.add(tomate);
		ingredient.add(oregano);
		ingredient.add(bacon);
		Dish dish8 = new Dish("Pizza bacon", 12.00f, "Cena",ingredient);

		setDishImage(dish8, "/sample_images/pizzaBacon.jpg");
		
		ingredient= new ArrayList<Ingredient>();
		ingredient.add(pan);
		ingredient.add(queso);
		ingredient.add(jamon);
		Dish dish9 = new Dish("Sándwich mixto", 10.00f, "Cena",ingredient);

		setDishImage(dish9, "/sample_images/sandwichMixto.jpg");
		
		// Sample users
		User user1 = new User("user1", "Julia","Martín","juliamartin@gmail.com",222222222, passwordEncoder.encode("pass"), "USER");
		userRepository.save(user1);
		User user = new User("user", "Julia","Martín","juliamartin@gmail.com",222222222, passwordEncoder.encode("pass"), "USER");
		userRepository.save(user);
		userRepository.save(new User("admin","Pepe","Pérez","pepeperez@gmail.com",111111111, passwordEncoder.encode("adminpass"), "USER", "ADMIN"));
		User a = new User("a","a","a","a@gmail.com",111111111, passwordEncoder.encode("a"), "USER");
		userRepository.save(a);
		
		List<Dish> dishes1= new ArrayList<Dish>();
		dishes1.add(dish5);
		dishes1.add(dish6);
		dishes1.add(dish7);
		
		List<Dish> dishes2= new ArrayList<Dish>();
		dishes2.add(dish2);
		dishes2.add(dish1);
		dishes2.add(dish4);
		
		List<Dish> dishes3= new ArrayList<Dish>();
		dishes3.add(dish3);
		dishes3.add(dish8);
		dishes3.add(dish9);
		
		List<Dish> dishes4= new ArrayList<Dish>();
		dishes4.add(dish4);
		dishes4.add(dish2);
		dishes4.add(dish4);
		
		List<Dish> dishes5= new ArrayList<Dish>();
		dishes5.add(dish1);
		dishes5.add(dish5);
		dishes5.add(dish9);
		
		// Sample purchases


		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		
		purchases.add(new Purchase("Azahara", "Andújar", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, dishes1, 3, 5, 2021));
		purchases.add(new Purchase("Javier", "Méndez", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, user, dishes2, 4, 7, 2021));
		purchases.add(new Purchase("David", "Andújar", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, dishes2, 12, 2, 2021));
		purchases.add(new Purchase("David", "Mestanza", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, user, dishes3, 8, 8, 2021));
		purchases.add(new Purchase("Azahara", "Méndez", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, dishes1, 24, 11, 2021));
		purchases.add(new Purchase("David", "Herrera", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, user, dishes5, 7, 5, 2021));
		purchases.add(new Purchase("Azahara", "Andújar", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, a, dishes5, 3, 5, 2021));
		purchases.add(new Purchase("Javier", "Méndez", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, a, dishes4, 4, 7, 2021));
		purchases.add(new Purchase("David", "Andújar", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, a, dishes5, 12, 2, 2021));
		purchases.add(new Purchase("David", "Mestanza", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, a, dishes3, 8, 8, 2021));
		purchases.add(new Purchase("Azahara", "Méndez", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user, dishes4, 24, 11, 2021));
		purchases.add(new Purchase("David", "Herrera", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, user, dishes4, 7, 5, 2021));
		purchases.add(new Purchase("Azahara", "Andújar", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, dishes1, 3, 5, 2021));
		purchases.add(new Purchase("Javier", "Méndez", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, user1, dishes3, 4, 7, 2021));
		purchases.add(new Purchase("David", "Andújar", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user, dishes5, 12, 2, 2021));
		purchases.add(new Purchase("David", "Mestanza", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, a, dishes4, 8, 8, 2021));
		purchases.add(new Purchase("Azahara", "Méndez", "Calle Tulipán SN",28934, "Móstoles", "España", 123456789, user1, dishes5, 24, 11, 2021));
		purchases.add(new Purchase("David", "Herrera", "Calle Rosa SN",28934, "Móstoles", "España", 987654321, a, dishes4, 7, 5, 2021));

		
		userRepository.save(user1);
		
		for (Purchase p:purchases) {
			for(Dish d:p.getDishes()) {
				p.setPrice(p.getPrice()+d.getDishPrice());
				d.setNbuy(d.getNbuy()+1);
			}
			
		}
		

		dishService.save(dish1);
		dishService.save(dish2);
		dishService.save(dish3);
		dishService.save(dish4);
		dishService.save(dish5);
		dishService.save(dish6);
		dishService.save(dish7);
		dishService.save(dish8);
		dishService.save(dish9);
		
		
		for(Purchase p : purchases)purchaseRepository.save(p);
		
	}

	public void setDishImage(Dish dish, String classpathResource) throws IOException {
		dish.setImage(true);
		Resource image = new ClassPathResource(classpathResource);
		dish.setImageFile(BlobProxy.generateProxy(image.getInputStream(), image.contentLength()));
	}

}

