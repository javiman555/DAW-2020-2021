package com.trec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trec.model.Dish;
import com.trec.repository.DishRepository;

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
}
