package com.trec.models;

import java.sql.Blob;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String category;
	private float dishPrice;
	
	@Lob
	@JsonIgnore
	private Blob dishImage;
	
	 @ManyToMany(mappedBy="dishes")
	 private List<Purchase> orders;

	 @ManyToMany
	 private List<Ingredient> ingredients;

	public Dish(String name, String category, float dishPrice) {
		this.name = name;
		this.category = category;
		this.dishPrice = dishPrice;
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

}