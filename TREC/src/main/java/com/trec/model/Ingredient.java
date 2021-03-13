package com.trec.model;

import java.util.List;

import javax.persistence.*;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private boolean isAllergen;
	private String nameAllergen;
	/*
	@ManyToMany(mappedBy="ingredients")
	private List<Dish> dishes;
*/
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

}