package com.project.firstclicks.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private AppUserSecurityService appUserSecurityService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		//Permite a  todos en home y en login
		return httpSecurity
			.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
			.authorizeHttpRequests( registry->{
			registry.requestMatchers("/home","/api/newTutor").permitAll();
			registry.requestMatchers("/admin/**").hasRole("ADMIN");
			registry.requestMatchers("/user/**").hasAnyRole("TUTOR","STUDENT","ADMIN");
			registry.anyRequest().authenticated();})
			//Cambia el /login a la ruta por donde queremos poner el html de login.
			.formLogin(httpSecurityFormLoginConfigurer -> {httpSecurityFormLoginConfigurer.loginPage("/login").permitAll();})			
		  .build();
		
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return appUserSecurityService;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
