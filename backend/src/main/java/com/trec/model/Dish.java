package com.trec.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Dish")
@DynamicUpdate
public class Dish {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	private String name;
	private float dishPrice;
	private String category;
	private int nbuy;

	@JsonIgnore
	@Lob
	private Blob imageFile;
	private boolean image;

	@ManyToMany
	 @JoinTable(
			   name="Dish_Ing",
			   joinColumns=@JoinColumn(name="Dish_id", referencedColumnName="id"),
			   inverseJoinColumns=@JoinColumn(name="Ing_id", referencedColumnName="id"))
	private List<Ingredient> ingredients;

	public Dish() {}

	public Dish(String name, float dishPrice,String category, List<Ingredient> ingredients) {
		super();
		this.name = name;
		this.dishPrice = dishPrice;
		this.category = category;
		this.ingredients = ingredients;
		this.nbuy = 0;
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
		return "Post [id="+id+", user=" + name + ", title=" + dishPrice + ", text=" + "]";
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNbuy() {
		return nbuy;
	}

	public void setNbuy(int nbuy) {
		this.nbuy = nbuy;
	}



}
