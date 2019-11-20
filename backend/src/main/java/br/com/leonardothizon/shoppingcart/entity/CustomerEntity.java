package br.com.leonardothizon.shoppingcart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class CustomerEntity {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private String[] roles;

	public CustomerEntity() {
		super();
	}

	public CustomerEntity(String firstName, String lastName, String username, String password, String[] roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		setPassword(password);
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
}