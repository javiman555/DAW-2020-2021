package com.trec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trec.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}