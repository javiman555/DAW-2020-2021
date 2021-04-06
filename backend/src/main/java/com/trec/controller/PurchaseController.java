package com.trec.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.trec.model.Dish;
import com.trec.model.User;
import com.trec.model.Purchase;
import com.trec.service.PurchaseService;
import com.trec.service.DishService;
import com.trec.service.UserService;

@Controller
public class PurchaseController extends DefaultModeAttributes{

	@Autowired
	private PurchaseService purchaseService;
	@Autowired
	private DishService dishService;
	@Autowired
	private UserService userService;

	
	@PostMapping("/adddish")
	public String addDish(Model model, Long id, HttpServletRequest request) {
		
		
		Principal principal = request.getUserPrincipal();
		String userNamexx = principal.getName();
		
		Dish dish = dishService.findById(id).get();
		User user = userService.findByName(userNamexx).get();
		if(user.getNewPurchase()==null){
			user.setNewPurchase(new Purchase());
			user.getNewPurchase().setDishes(new ArrayList<Dish>() );
		}
		user.getNewPurchase().getDishes().add(dish);
		user.getNewPurchase().setPrice(user.getNewPurchase().getPrice()+dish.getDishPrice());
		userService.save(user);
		purchaseService.save(user.getNewPurchase());
		
		model.addAttribute("dishes1", dishService.getByCategory("Desayuno"));
		model.addAttribute("dishes2", dishService.getByCategory("Comida"));
		model.addAttribute("dishes3", dishService.getByCategory("Cena"));
		
		return "menu";
	}
	
	@GetMapping("/cart")
	public String showPurchase(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userNamexx = principal.getName();
		Purchase newPurchase = new Purchase();
		User user = userService.findByName(userNamexx).get();
		if(user.getNewPurchase()!=null) {
			newPurchase =userService.findByName(userNamexx).get().getNewPurchase();
		}else {
		newPurchase.setUser(user);	
		user.setNewPurchase(newPurchase);
		userService.save(user);
		}
			model.addAttribute("dishes", newPurchase.getDishes());
			model.addAttribute("price", newPurchase.getPrice());
			
		return "cart";
	}
	@GetMapping("/profile/{id}")
	public String showProfile(Model model, @PathVariable long id, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Optional<User> user = userService.findById(id);
		
		if (userReal.get().getId() == user.get().getId()) {
			
			if(userReal.get().getRoles().contains("ADMIN")){
				model.addAttribute("adminpurchases", purchaseService.findAll(0));
				model.addAttribute("graficid", purchaseService.findIdAll());
				model.addAttribute("graficprice", purchaseService.findPriceAll());
				model.addAttribute("isadminempty", purchaseService.findAll(0).isEmpty());			
			} else {
				model.addAttribute("purchases", purchaseService.getByUser(user.get(), 0));
				model.addAttribute("isempty", purchaseService.getByUser(user.get(), 0).isEmpty());
				model.addAttribute("dishesRecomended", dishService.getRecomended(user.get().getId()));
			}

			model.addAttribute("user", user.get());
			
			model.addAttribute("currentPage", 0);
			
			return "profile";
		} else {
			return "error";
		}
	}

	@GetMapping("/purchase/{id}")
	public String showPurchase(Model model, @PathVariable long id, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		Optional<User> userReal = userService.findByName(userNameReal);
		Purchase purchase = purchaseService.findById(id).get();
		
		if(userReal.get().getRoles().contains("ADMIN") || userReal.get().getId() == purchase.getUser().getId() ) {
			
			model.addAttribute("purchase",purchase);
		
			return "purchase";
		}else {
			return "error";
		}
	
	}
	@GetMapping("/pay")
	public String pagar(Model model, HttpServletRequest request) {
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();
		User userReal = userService.findByName(userNameReal).get();
		if (!userReal.getNewPurchase().getDishes().isEmpty()) {
			model.addAttribute("price", userReal.getNewPurchase().getPrice());
			return "pay";
		}
		
		model.addAttribute("dishes1", dishService.getByCategory("Desayuno"));
		model.addAttribute("dishes2", dishService.getByCategory("Comida"));
		model.addAttribute("dishes3", dishService.getByCategory("Cena"));
		
		return "/menu";
		
	}
	@PostMapping("/processpay")
	public String processPay(Model model, Purchase purchase, HttpServletRequest request) {
		
		Principal principal = request.getUserPrincipal();
		String userNameReal = principal.getName();

		User userReal = userService.findByName(userNameReal).get();
		Purchase newPurchase = userReal.getNewPurchase();

		newPurchase.setFirstName(purchase.getFirstName());
		newPurchase.setSurname(purchase.getSurname());
		newPurchase.setAddress(purchase.getAddress());
		newPurchase.setPostalCode(purchase.getPostalCode());
		newPurchase.setCity(purchase.getCity());
		newPurchase.setCountry(purchase.getCountry());
		newPurchase.setPhoneNumber(purchase.getPhoneNumber());
		Calendar c = Calendar.getInstance();
		newPurchase.setDateDay(c.get(Calendar.DATE));
		newPurchase.setDateMonth(c.get(Calendar.MONTH));
		newPurchase.setDateYear(c.get(Calendar.YEAR));
		newPurchase.setUser(userReal);
		
		for (Dish dish : newPurchase.getDishes()) {
			dish.setNbuy(dish.getNbuy()+1);
			dishService.save(dish);
		}
		
		purchaseService.save(newPurchase);
		userReal.setNewPurchase(null);
		userReal.getPurchases().add(newPurchase);
		userService.save(userReal);
		
		return "/paydone";
	}
	
	public String showPurchase(Model model, @PathVariable long id) {
		
		model.addAttribute("purchase", purchaseService.findById(id).get());
		
		return "/paydone";
	}
}
