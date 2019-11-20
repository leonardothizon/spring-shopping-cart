package br.com.leonardothizon.shoppingcart.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class OrderEntity {

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	private Double totalOrder;
	private LocalDateTime created;
	private String comments;

	@OneToMany(mappedBy = "order")
	@JsonIgnoreProperties("order")
	private Set<OrderProductEntity> items;

	public OrderEntity() {
		super();
	}

	public OrderEntity(CustomerEntity customer, Double totalOrder, LocalDateTime created, String comments) {
		this.customer = customer;
		this.totalOrder = totalOrder;
		this.created = created;
		this.comments = comments;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public Double getTotalOrder() {
		return totalOrder;
	}

	public void setTotalOrder(Double totalOrder) {
		this.totalOrder = totalOrder;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Set<OrderProductEntity> getItems() {
		return items;
	}

	public void setItems(Set<OrderProductEntity> items) {
		this.items = items;
	}

}