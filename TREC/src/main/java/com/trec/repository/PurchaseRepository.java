package com.trec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trec.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}