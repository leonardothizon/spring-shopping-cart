package br.com.leonardothizon.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.leonardothizon.shoppingcart.entity.ProductEntity;

public interface ProductRepository extends CrudRepository<ProductEntity, Long>{

}