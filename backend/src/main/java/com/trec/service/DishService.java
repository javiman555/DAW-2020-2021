package com.trec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.repository.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;
	@Autowired
	private PurchaseService purchaseService;

	public Optional<Dish> findById(long id) {
		return dishRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return dishRepository.existsById(id);
	}

	public List<Dish> findAll() {
		return dishRepository.findAll();
	}

	public void save(Dish book) {
		dishRepository.save(book);
	}

	public void deleteById(long id) {
		dishRepository.deleteById(id);
	}
	
	public List<Dish> getByCategory(String category){
		return dishRepository.getByCategory(category);
	}
	public List<Dish> getRecomended(long id){
		return dishRepository.getRecomended(id);
	}
	
	public Dish updateDish(Dish oldish,Dish changedish) {
		
		Dish newdish = oldish;
		
		if(changedish.getName() != oldish.getName()) {
			newdish.setName(changedish.getName());
		}
		if(changedish.getDishPrice() != oldish.getDishPrice()) {
			newdish.setDishPrice(changedish.getDishPrice());
		}
		if(changedish.getCategory() != oldish.getCategory()) {
			newdish.setCategory(changedish.getCategory());
		}
		if(changedish.getIngredients() != oldish.getIngredients() && changedish.getIngredients() != null ) {
			newdish.setIngredients(changedish.getIngredients());
		}
		
		return newdish;
	}
	
	public boolean deleteDish(Optional<Dish> dish,long id) {
		
		if (dish.isPresent()) {
			List<Purchase> purchases = purchaseService.findAll();
			List<Dish> dishes = null;
			Dish dishon = null;
			int dishIs;
			for (Purchase purchase : purchases) {
				dishIs = 0;
				dishes = purchase.getDishes();
				for (Dish d : dishes) {
					
					if (d.getId().equals(id)) {
						dishon = d;
						dishIs = dishIs+1;
					}	
				}
				
				if (dishIs > 0) {
					while(dishIs > 0) {
						dishes.remove(dishon);
						dishIs = dishIs -1;
					}
					
					purchase.setDishes(dishes);
					purchaseService.save(purchase);
				}
			}
			dish.get().setIngredients(null);
			this.deleteById(id);
			return true;
			
		} else {
			return false;
		}
	}
	
	
	
}
