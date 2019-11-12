package br.com.leonardothizon.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DetailsService detailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//         .authorizeRequests().anyRequest().authenticated()
//				.authorizeRequests().antMatchers("**/checkout/**").hasRole("ADMIN").and().httpBasic();
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();

//		http.authorizeRequests()
//        .anyRequest().authenticated()
//        .and()
//        .httpBasic()
//        .and()
//        .csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService).passwordEncoder(UserEntity.PASSWORD_ENCODER);
//        auth.inMemoryAuthentication()
//            .withUser("admin")
//            .password("{noop}password")
//            .roles("USER");
	}
}