package br.com.leonardothizon.shoppingcart.controller;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leonardothizon.shoppingcart.entity.ProductEntity;
import br.com.leonardothizon.shoppingcart.repository.ProductRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	ProductRepository products;

	@GetMapping("")
    public List<ProductEntity> getAllProducts() {
		return (List<ProductEntity>) products.findAll();
    }
	
	@GetMapping("/{id}")
	public ProductEntity getProduct(@PathVariable @Min(1) Long id) throws Exception {
		return products.findById(id).orElseThrow(() -> new Exception("Product not found"));
	}
	
}
