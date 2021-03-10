package com.trec.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trec.models.Dish;
import com.trec.repositories.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;

	public Optional<Dish> findById(long id) {
		return dishRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return dishRepository.existsById(id);
	}

	public List<Dish> findAll() {
		return dishRepository.findAll();
	}

	public void save(Dish dish) {
		dishRepository.save(dish);
	}

	public void delete(long id) {
		dishRepository.deleteById(id);
	}


}