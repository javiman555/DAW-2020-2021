package com.trec.services;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.trec.models.Dish;

@Service
public class DishService {

	private ConcurrentMap<Long, Dish> dishes = new ConcurrentHashMap<>();
	private AtomicLong nextId = new AtomicLong();

	public DishService() {
		save(new Dish("Cereales con leche", 5.00f, "Desayuno","Cena"));
		save(new Dish("Tortilla de patatas", 5.00f, "Comida", "Cena"));
		save(new Dish("Pollo a la plancha", 5.00f, "Cena","Cena"));
		save(new Dish("Canelones", 5.00f, "Comida","Cena"));
	}
	
	public Collection<Dish> findAll() {
		return dishes.values();
	}

	public Dish findById(long id) {
		return dishes.get(id);
	}

	public void save(Dish dish) {

		long id = nextId.getAndIncrement();

		dish.setId(id);

		this.dishes.put(id, dish);
	}

	public void deleteById(long id) {
		this.dishes.remove(id);
	}

}