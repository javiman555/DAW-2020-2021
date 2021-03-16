package com.trec.model;

import java.sql.Blob;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;	
	private String surname;	
	private String email;	
	private int phoneNumber;

	@Lob
	private Blob imageFile;
	private boolean image;
	
	private String name;

	private String encodedPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	@OneToOne
	private Purchase newPurchase;
	@OneToMany
	private List<Purchase> purchases;
	
	public User() {
	}

	public User(String name, String firstName, String surname, String email, int phoneNumber, String encodedPassword, String... roles) {
		this.name = name;
		this.encodedPassword = encodedPassword;
		this.roles = List.of(roles);
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEncodedPassword() {
		return encodedPassword;
	}

	public void setEncodedPassword(String encodedPassword) {
		this.encodedPassword = encodedPassword;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	public Purchase getNewPurchase() {
		return newPurchase;
	}

	public void setNewPurchase(Purchase newPurchase) {
		this.newPurchase = newPurchase;
	}
	

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

}