package com.trec.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trec.model.Dish;
import com.trec.model.Purchase;
import com.trec.model.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	 @Query("SELECT p FROM Purchase p WHERE p.user = :u")
	 public Page<Purchase> getByUser(User u, Pageable page);
	 
}