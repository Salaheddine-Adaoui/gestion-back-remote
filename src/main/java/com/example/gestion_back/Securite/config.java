package com.example.gestion_back.Securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class config {
	
	@Autowired
	jwtFilter jwtfilter;
	
	@Autowired
	CustomUserDetailsService userserv;
	
	
	@SuppressWarnings("deprecation")
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                .requestMatchers("/auth","/newprof","/user/**","/newadmin").permitAll()
                //.requestMatchers("/user/hello").hasRole("CLIENT")
                .anyRequest().authenticated() 
            )
            .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
            .csrf().disable();

        return http.build(); // Retourne le SecurityFilterChain configuré
    }
	
	
	
	@Bean
	public AuthenticationProvider prov() {
		DaoAuthenticationProvider p=new DaoAuthenticationProvider();
		p.setUserDetailsService(userdetailsservice());
		p.setPasswordEncoder(bCryptPasswordEncoder());
		return p;
		
	}
	

	@Bean
	public AuthenticationManager authenticationMnager(){
		return new ProviderManager(prov());
	}
	
	@Bean
	public UserDetailsService userdetailsservice() {
		return userserv;
	}
	
	
	
	@Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
