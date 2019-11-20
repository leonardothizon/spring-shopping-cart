package br.com.leonardothizon.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.leonardothizon.shoppingcart.entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long>{

	CustomerEntity findByUsername(String username);

}