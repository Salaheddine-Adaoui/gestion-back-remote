package com.example.gestion_back.Securite;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
public class myAuthenticationUser extends UsernamePasswordAuthenticationToken {

	

    private final String firstName;
    private final String lastName;
    
	public myAuthenticationUser(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities,String first,String last) {
		super(principal, credentials, authorities);
		// TODO Auto-generated constructor stub
		firstName=first;
		lastName=last;
		
	}

	
    
}
