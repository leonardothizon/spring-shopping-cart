package br.com.leonardothizon.shoppingcart;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShoppingCartApplication {

	@Autowired
	private UserRepository users;
	
	@PostConstruct
	private void init() {
		System.out.println("Inserting user admin");
		UserEntity user = new UserEntity();
		user.setFirstName("Admin");
		user.setUsername("admin");
		user.setPassword("password");
		user.setRoles(new String[]{"ADMIN"});
		users.save(user);
		System.out.println("User inerted");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

}
