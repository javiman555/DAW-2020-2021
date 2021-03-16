package com.trec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.model.User;
import com.trec.repository.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	public Optional<Purchase> findById(long id) {
		return purchaseRepository.findById(id);
	}
	
	public boolean exist(long id) {
		return purchaseRepository.existsById(id);
	}

	public List<Purchase> findAll() {
		return purchaseRepository.findAll();
	}

	public void save(Purchase purchase) {
		purchaseRepository.save(purchase);
	}

	public void delete(long id) {
		purchaseRepository.deleteById(id);
	}
	
	public List<Purchase> getByUser(User user){
		return purchaseRepository.getByUser(user);
	}
}
