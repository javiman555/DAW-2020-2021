package com.trec.models;

import java.util.List;

import javax.persistence.*;

@Entity
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;
	private String surname;
	private String address;
	private int postalCode;
	private String city;
	private String country;
	private int phoneNumber;
	private float price;
	/*
	@ManyToMany
	private List<Dish> dishes;
	*/
	public Purchase() {
	}


	public Long getId() {
		return id;
	}

}