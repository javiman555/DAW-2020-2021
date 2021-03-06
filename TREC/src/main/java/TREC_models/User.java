package TREC_models;

import java.sql.Blob;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import javax.persistence.*;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String encodedPassword;	
	
	private String firstName;	
	private String surname;	
	private String email;	
	private int phoneNumber;

	@Lob
	@JsonIgnore
	private Blob imageFile;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	
	@OneToMany(cascade = CascadeType.ALL)
	 private List<Order> orders; 

	public User() {
	}

	public User(String name, String encodedPassword, String... roles) {
		this.name = name;
		this.encodedPassword = encodedPassword;
		this.roles = List.of(roles);
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

}



/*public class User {

	private Long id;
	private String name;
	private String surname;

	public User() {

	}

	public User(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	@Override
	public String toString() {
		return "User [id="+id+", name=" + name + ", surname=" + surname + "]";
	}

}*/
