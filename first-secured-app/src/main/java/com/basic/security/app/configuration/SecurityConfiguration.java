package com.basic.security.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MyAuthenticationProvider authenticationProvider;
	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { InMemoryUserDetailsManager userDetailsManager = new
	 * InMemoryUserDetailsManager(); UserDetails user =
	 * User.withUsername("gowtham").password(passwordEncoder.encode("password")).
	 * authorities("read").build(); userDetailsManager.createUser(user);
	 * auth.userDetailsService(userDetailsManager); }
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* http.httpBasic(); */
		http.formLogin();
		http.authorizeRequests().antMatchers("/hello").authenticated().anyRequest().denyAll();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
