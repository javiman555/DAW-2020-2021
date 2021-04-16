package com.trec.service;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.model.User;
import com.trec.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private DishService dishService;
	@Autowired
	private UserService userService;

	public Optional<Purchase> findById(long id) {
		return purchaseRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return purchaseRepository.existsById(id);
	}

	public void save(Purchase purchase) {
		purchaseRepository.save(purchase);
	}

	public void delete(long id) {
		purchaseRepository.deleteById(id);
	}
	
	public Page<Purchase> getByUser(User user, int n){
		return purchaseRepository.getByUser(user, PageRequest.of(n, 5));
	}
	
	public List<Purchase> findAll() {
		return purchaseRepository.findAll();
	}
	
	 public Page<Purchase> findAll(int n){
		 return purchaseRepository.findAll(PageRequest.of(n, 5));
	 }
	 
	public List<Long> findIdAll() {
		return purchaseRepository.findIdAll();
	}
	public List<Float> findPriceAll() {
		return purchaseRepository.findPriceAll();
	}
	
	public void fillPurchase(Purchase purchase, Principal principal,User userReal) {
		
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
		
		this.save(newPurchase);
		userReal.setNewPurchase(null);
		userReal.getPurchases().add(newPurchase);
		userService.save(userReal);
	}
}
