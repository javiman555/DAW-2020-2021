package com.trec.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private String name;
	private float dishPrice;
	private String category;
	private String ingredient;
	
	@Column(columnDefinition = "TEXT")
	private String description;

	@Lob
	private Blob imageFile;

	private boolean image;

	@ManyToMany
	private List<Ingredient> ingredients;

	public Dish() {}

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
	
	public Blob getImageFile() {
		return imageFile;
	}

	public void setImageFile(Blob image) {
		this.imageFile = image;
	}

	public boolean hasImage(){
		return this.image;
	}

	public void setImage(boolean image){
		this.image = image;
	}
	public boolean getImage() {
		return image;
	}

	@Override
	public String toString() {
		return "Post [id="+id+", user=" + name + ", title=" + dishPrice + ", text=" + ingredient + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
