package TREC_models;

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
	 private List<Order> orders;

	 @ManyToMany
	 private List<Ingredient> ingredients;
	 
	public Dish() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}