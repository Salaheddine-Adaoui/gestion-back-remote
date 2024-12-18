package com.example.gestion_back.Securite;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
	
	

	    public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



		private String email;
	    private String password;
	    private String role;
	   
        
	   
	    public CustomUserDetails(String email, String password, String role) {
			super();
			this.email = email;
			this.password = password;
			this.role = role;
		}

	

	    @Override
	    public boolean isAccountNonExpired() {
	        return true; // Changez cela si vous avez une logique pour l'expiration de l'account
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return true; // Changez cela si vous avez une logique pour le verrouillage du compte
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true; // Changez cela si vous avez une logique pour l'expiration des credentials
	    }

	    

	    // Ajoutez des getters si nécessaire
	    public String getRole() {
	        return role;
	    }

		


		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return List.of(new SimpleGrantedAuthority("ROLE_" + role)); 
		}



		@Override
		public String getUsername() {
			// TODO Auto-generated method stub
			return null;
		}


}
