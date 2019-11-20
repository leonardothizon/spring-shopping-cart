package br.com.leonardothizon.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.leonardothizon.shoppingcart.entity.OrderProductEntity;

public interface OrderProductRepository extends CrudRepository<OrderProductEntity, Long>{

}