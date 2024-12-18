package com.example.gestion_back.Securite;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Entities.Compte;
import com.example.gestion_back.Repository.userRepo;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	userRepo userrepo;

	@Override
	public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Compte> u=userrepo.findByEmail(email);
		if(u.isPresent()) {
			Compte user =u.get();
	
			return new CustomUserDetails(user.getEmail(),user.getPassword(),user.getRole());
					
		}
		else {
			throw new UsernameNotFoundException("user not found");
		}
	}

}
