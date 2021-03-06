package TREC_models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String nameAllergen;
	private boolean isAllergen;
	
	@ManyToMany(mappedBy="ingredients")
	private List<Dish> dishes;


	public Ingredient() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}