package com.project.firstclicks.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled=true)
public class SecurityConfiguration {
	
	@Autowired
	private AppUserSecurityService appUserSecurityService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		//Permite a  todos en home y en login
		return httpSecurity
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests( req->{
			req.requestMatchers("/home","/api/newTutor").permitAll();
			req.requestMatchers("/admin/**").hasRole("ADMIN");
			req.requestMatchers("/user/**").hasAnyRole("TUTOR","STUDENT","ADMIN");
			req.anyRequest().authenticated();})
			.authenticationProvider(authenticationProvider())
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
