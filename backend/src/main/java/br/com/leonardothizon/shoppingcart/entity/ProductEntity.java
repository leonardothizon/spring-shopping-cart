package br.com.leonardothizon.shoppingcart.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductEntity {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private Double price;
	private LocalDateTime created;

	public ProductEntity() {
		super();
	}

	public ProductEntity(String name, String description, Double price, LocalDateTime created) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.created = created;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	
}