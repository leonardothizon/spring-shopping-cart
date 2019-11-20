package br.com.leonardothizon.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.leonardothizon.shoppingcart.controller.OrderController;
import br.com.leonardothizon.shoppingcart.controller.ProductController;
import br.com.leonardothizon.shoppingcart.entity.CustomerEntity;
import br.com.leonardothizon.shoppingcart.entity.OrderEntity;
import br.com.leonardothizon.shoppingcart.entity.OrderProductEntity;
import br.com.leonardothizon.shoppingcart.entity.ProductEntity;

@SpringBootTest
class ShoppingCartApplicationTests {
	
	@Autowired
	private ProductController productController;

	@Autowired
	private OrderController orderController;

	@Test
	void getAllProducts() {
		List<ProductEntity> products = productController.getAllProducts();
		assertEquals(10, products.size());
	}
	
	@Test
	void getProductById() throws Exception {
		ProductEntity product = productController.getProduct(4L);
		assertEquals("Batom roxo", product.getName());
		assertEquals(6.99, product.getPrice());
	}
	
	@Test
	void testOrderCreation() throws Exception {
		CustomerEntity customer = new CustomerEntity();
		customer.setId(1L);
		OrderEntity order = new OrderEntity(customer, 100.0, LocalDateTime.now(), "Order comments");
		Set<OrderProductEntity> items = new LinkedHashSet<OrderProductEntity>();
		items.add(new OrderProductEntity(order, productController.getProduct(4L), 1.0, 12.0));
		order.setItems(items);
		order = orderController.postOrder(order);
		assertNotNull(order.getId());
	}
	
	@Test
	void testItemsTotalPrice() throws Exception {
		CustomerEntity customer = new CustomerEntity();
		customer.setId(1L);
		OrderEntity order = new OrderEntity(customer, 100.0, LocalDateTime.now(), "Order comments");
		Set<OrderProductEntity> items = new LinkedHashSet<OrderProductEntity>();
		items.add(new OrderProductEntity(order, productController.getProduct(4L), 3.0, 1.0));
		items.add(new OrderProductEntity(order, productController.getProduct(6L), 1.0, 10.0));
		items.add(new OrderProductEntity(order, productController.getProduct(5L), 2.0, 10.0));
		order.setItems(items);
		order = orderController.postOrder(order);
		List<OrderProductEntity> orderItems = new ArrayList<>(order.getItems());
		assertEquals(3.0, orderItems.get(0).getTotalPrice());
		assertEquals(10.0, orderItems.get(1).getTotalPrice());
		assertEquals(20.0, orderItems.get(2).getTotalPrice());
	}
	
	@Test
	void testOrderTotalValue() throws Exception {
		CustomerEntity customer = new CustomerEntity();
		customer.setId(1L);
		OrderEntity order = new OrderEntity(customer, 100.0, LocalDateTime.now(), "Order comments");
		Set<OrderProductEntity> items = new LinkedHashSet<OrderProductEntity>();
		items.add(new OrderProductEntity(order, productController.getProduct(4L), 1.0, 12.0));
		items.add(new OrderProductEntity(order, productController.getProduct(6L), 1.0, 12.0));
		order.setItems(items);
		order = orderController.postOrder(order);
		assertEquals(24.0, order.getTotalOrder());
	}

}
