package br.com.leonardothizon.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.leonardothizon.shoppingcart.entity.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Long>{

}