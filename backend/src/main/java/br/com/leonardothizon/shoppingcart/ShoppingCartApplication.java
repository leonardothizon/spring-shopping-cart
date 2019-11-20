package br.com.leonardothizon.shoppingcart;

import java.time.LocalDateTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.leonardothizon.shoppingcart.entity.CustomerEntity;
import br.com.leonardothizon.shoppingcart.entity.ProductEntity;
import br.com.leonardothizon.shoppingcart.repository.CustomerRepository;
import br.com.leonardothizon.shoppingcart.repository.ProductRepository;

@SpringBootApplication
public class ShoppingCartApplication {

	@Autowired
	private CustomerRepository customers;

	@Autowired
	private ProductRepository products;

	@PostConstruct
	private void init() {
		System.out.println("Initializing system....");

		customers.save(new CustomerEntity("Leonardo", "Thizon", "leonardo", "leonardo", new String[] { "ADMIN", "USER" }));
		customers.save(new CustomerEntity("Tobias", "Silva", "tobias", "tobias", new String[] { "USER" }));
		customers.save(new CustomerEntity("Gabriela", "Schlickmann", "gabi", "gabi", new String[] { "USER" }));

		products.save(new ProductEntity("Batom roxo", "", 6.99, LocalDateTime.now()));
		products.save(new ProductEntity("Batom vermelho", "", 6.99, LocalDateTime.now()));
		products.save(new ProductEntity("Batom rosa", "", 6.99, LocalDateTime.now()));
		products.save(new ProductEntity("Esmalte rosa", "", 3.99, LocalDateTime.now()));
		products.save(new ProductEntity("Esmalte preto", "", 3.99, LocalDateTime.now()));
		products.save(new ProductEntity("Batom vermelho", "", 6.99, LocalDateTime.now()));
		products.save(new ProductEntity("Creme corporal", "Creme antienvelhecimento para o corpo.", 14.59,
				LocalDateTime.now()));
		products.save(new ProductEntity("Perfume One Milion", "Perfume importado", 159.00, LocalDateTime.now()));
		products.save(new ProductEntity("Perfume 212 Vip", "Perfume importado", 149.00, LocalDateTime.now()));
		products.save(new ProductEntity("Removedor de Maquiagem", "", 24.99, LocalDateTime.now()));

		System.out.println("System initialized.");
	}

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

}
