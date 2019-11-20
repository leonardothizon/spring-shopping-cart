package br.com.leonardothizon.shoppingcart.controller;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardothizon.shoppingcart.entity.OrderEntity;
import br.com.leonardothizon.shoppingcart.repository.CustomerRepository;
import br.com.leonardothizon.shoppingcart.repository.OrderProductRepository;
import br.com.leonardothizon.shoppingcart.repository.OrderRepository;
import br.com.leonardothizon.shoppingcart.repository.ProductRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

	@Autowired
	OrderRepository orders;

	@Autowired
	ProductRepository products;

	@Autowired
	CustomerRepository customers;

	@Autowired
	OrderProductRepository orderProducts;

	@GetMapping("")
	public List<OrderEntity> getAllOrders() {
		return (List<OrderEntity>) orders.findAll();
	}

	@GetMapping("/{id}")
	public OrderEntity getOrder(@PathVariable @Min(1) Long id) throws Exception {
		return orders.findById(id).orElseThrow(() -> new Exception("Product not found"));
	}

	@PostMapping("")
	public OrderEntity postOrder(@RequestBody OrderEntity order) throws Exception {
		Calendar c1 = Calendar.getInstance();
		if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			throw new Exception("Não é possível incluir pedidos aos finais de semana");
		}
		order.setCustomer(customers.findById(order.getCustomer().getId()).orElse(null));
		order.setCreated(LocalDateTime.now());
		order.getItems().forEach(op -> {
			op.setProduct(products.findById(op.getProduct().getId()).orElse(null));
			op.setCreated(LocalDateTime.now());
			op.setTotalPrice(op.getQuantity() * op.getUnityPrice());
		});
		order.setTotalOrder(order.getItems().stream().mapToDouble(op -> op.getTotalPrice()).sum());
		OrderEntity newOrder = orders.save(order);
		order.getItems().forEach(op -> {
			op.setOrder(newOrder);
			orderProducts.save(op);
		});
		return newOrder;
	}

}
