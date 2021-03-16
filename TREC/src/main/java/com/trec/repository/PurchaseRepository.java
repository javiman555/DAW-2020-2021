package com.trec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	 @Query("SELECT p FROM Purchase p WHERE p.user = :u")
	 public List<Purchase> getByUser(User u);
	 
}