package org.cp.projet.comptes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// les utilisateurs qui peuvent avoir accès à l'appli, ils sont stockés en mémoire
		auth.inMemoryAuthentication().withUser("fatima").password("{noop}fff").roles("USER", "ADMIN");
		auth.inMemoryAuthentication().withUser("mounir").password("{noop}mmm").roles("USER");	
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// formulaire de spring 
		http.formLogin();
		// on définit les parties sécurisés 
		http.authorizeRequests().antMatchers("/comptes","/consulterCompte").hasRole("USER");
		http.authorizeRequests().antMatchers("/saveOperation**/**").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
	}

}
