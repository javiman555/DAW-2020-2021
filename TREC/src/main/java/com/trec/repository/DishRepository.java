package com.trec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trec.model.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

}