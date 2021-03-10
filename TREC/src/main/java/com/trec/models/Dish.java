package com.trec.models;

import java.sql.Blob;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dish {
	@Id
	private Long id;
	
	private String name;
	private float dishPrice;
	private String category;
	private String ingredient;
	
	 @ManyToMany
	 private List<Ingredient> ingredients;
	 
	public Dish() {

	}

	public Dish(String name, float dishPrice, String ingredient, String category) {
		super();
		this.name = name;
		this.dishPrice = dishPrice;
		this.ingredient = ingredient;
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(float dishPrice) {
		this.dishPrice = dishPrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public String toString() {
		return "Post [id="+id+", user=" + name + ", title=" + dishPrice + ", text=" + ingredient + "]";
	}

}

/*
@Entity
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String category;
	private float dishPrice;
	private String ingredient;
	
	@Lob
	@JsonIgnore
	private Blob dishImage;
	
	 @ManyToMany(mappedBy="dishes")
	 private List<Purchase> orders;

	 @ManyToMany
	 private List<Ingredient> ingredients;

	public Dish(String name, String category, float dishPrice, String ingredient) {
		this.name = name;
		this.category = category;
		this.dishPrice = dishPrice;
		this.ingredient = ingredient;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public float getDishPrice() {
		return dishPrice;
	}


	public void setDishPrice(float dishPrice) {
		this.dishPrice = dishPrice;
	}


	public String getIngredient() {
		return ingredient;
	}


	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}


	public Blob getDishImage() {
		return dishImage;
	}


	public void setDishImage(Blob dishImage) {
		this.dishImage = dishImage;
	}


	public List<Purchase> getOrders() {
		return orders;
	}


	public void setOrders(List<Purchase> orders) {
		this.orders = orders;
	}


	public List<Ingredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

}
*/