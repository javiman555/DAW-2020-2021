package com.trec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trec.model.Dish;
import com.trec.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}