package br.com.leonardothizon.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.leonardothizon.shoppingcart.entity.CustomerEntity;
import br.com.leonardothizon.shoppingcart.repository.CustomerRepository;

@Component
public class DetailsService implements UserDetailsService {
	
	@Autowired
	CustomerRepository customers;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Looking for user " + username);
		CustomerEntity user = customers.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username + " was not found");
		}
		System.out.println("User found " + user.getFirstName());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRoles()));
	}

}
