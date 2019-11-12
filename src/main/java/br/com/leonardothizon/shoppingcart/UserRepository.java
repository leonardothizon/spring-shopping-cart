package br.com.leonardothizon.shoppingcart;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

	UserEntity findByUsername(String username);

}