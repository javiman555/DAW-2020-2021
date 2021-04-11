package com.trec.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ingredient")
public class Ingredient {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private boolean isAllergen;
	private String nameAllergen;
	
	@ManyToMany(mappedBy="ingredients")
	
	@JsonIgnore
	private List<Dish> dishes;

	public Ingredient() {
		
	}
	
	public boolean isAllergen() {
		return isAllergen;
	}

	public void setAllergen(boolean isAllergen) {
		this.isAllergen = isAllergen;
	}

	public String getNameAllergen() {
		return nameAllergen;
	}

	public void setNameAllergen(String nameAllergen) {
		this.nameAllergen = nameAllergen;
	}

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public Ingredient(String name, String nameAllergen) {
		this.name = name;
		if (nameAllergen.isEmpty()) {
			this.isAllergen = false;
			this.nameAllergen = null;
		} else {
			this.isAllergen = true;
			this.nameAllergen = nameAllergen;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}