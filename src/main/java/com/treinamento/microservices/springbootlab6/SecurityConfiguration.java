package com.treinamento.microservices.springbootlab6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("barry").password("{noop}t0ps3cr3t").roles("USER")
				.and()
				.withUser("john").password("{noop}t0ps3cr3t").roles("MANAGER");
	}*/

	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.passwordEncoder(NoOpPasswordEncoder.getInstance())
				.withUser("barry").password("t0ps3cr3t").roles("USER")
				.and()
				.withUser("john").password("t0ps3cr3t").roles("MANAGER");
	}*/

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication()
				.withUser("barry").password("$2a$04$KvWUVQh1M08ircH9WtxHKuEmEAsYxMAWwJ9hGLqUwv20wosE3Ad5C").roles("USER")
				.and()
				.withUser("john").password("$2a$04$KvWUVQh1M08ircH9WtxHKuEmEAsYxMAWwJ9hGLqUwv20wosE3Ad5C").roles("MANAGER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/foo/**").hasAnyRole("USER")
				.antMatchers("/bar/**").hasAnyRole("MANAGER")
				.anyRequest().fullyAuthenticated()
				.and().httpBasic().and().csrf().disable();
	}
}
