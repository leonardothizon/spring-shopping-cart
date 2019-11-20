package br.com.leonardothizon.shoppingcart.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_products")
public class OrderProductEntity {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;

	private Double quantity;
	private LocalDateTime created;
	private Double unityPrice;
	private Double totalPrice;

	public OrderProductEntity() {
		super();
	}

	public OrderProductEntity(OrderEntity order, ProductEntity product, Double quantity, Double unityPrice) {
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.unityPrice = unityPrice;
		this.created = LocalDateTime.now();
		this.totalPrice = quantity * unityPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public Double getUnityPrice() {
		return unityPrice;
	}

	public void setUnityPrice(Double unityPrice) {
		this.unityPrice = unityPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}