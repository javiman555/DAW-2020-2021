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
		save(new Dish("Cereales con leche", "Desayuno", 5.00f));
		save(new Dish("Tortilla de patatas", "Comida", 3.50f));
		save(new Dish("Pollo a la plancha", "Cena", 5.50f));
		save(new Dish("Canelones", "Comida", 8.00f));
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
