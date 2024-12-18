package com.example.gestion_back.Securite;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class jwtUtils {
	

	private static  String secretkey="kwXQP+D5CJv36BrKY9sIzIWldQzRMthEbUz6qz+30j+bbt1gxoJrJ7fky69sXhyn6R0GdfUPkjGp4iOW8UqKGg==";
	private static final long validity=33600000;
	
	@SuppressWarnings("unchecked")
	public String generateToken(CustomUserDetails userdetails) {
		@SuppressWarnings("rawtypes")
		Map<String,Object> h=new HashMap();h.put("role", String.valueOf(userdetails.getAuthorities()));
		return Jwts.builder()
		.setClaims(h)
		.setSubject(userdetails.getEmail())
		.setIssuedAt(Date.from(Instant.now()))
		.setExpiration(Date.from(Instant.now().plusMillis(validity)))
		.signWith(SignatureAlgorithm.HS512,secretkey).compact();
	}
	
	public String extractUsername(String token) {
        // Parse le token JWT pour extraire les revendications (claims)
        @SuppressWarnings("deprecation")
		Jws<Claims> claims = Jwts.parser()
                .setSigningKey(secretkey)
                .parseClaimsJws(token);

        // Récupère le sujet (nom d'utilisateur) à partir des claims du JWT
        return claims.getBody().getSubject();
    }
	
	public String extractRole(String token) {
		@SuppressWarnings("deprecation")
		Jws<Claims> claims = Jwts.parser()
			.setSigningKey(secretkey)
			.parseClaimsJws(token);

		// Récupérer la revendication "role" et la retourner comme une chaîne
		return claims.getBody().get("role", String.class);
	}
	
	

	public boolean isvalid(String token) {
		 @SuppressWarnings("deprecation")
		Jws<Claims> claims = Jwts.parser()
	                .setSigningKey(secretkey)
	                .parseClaimsJws(token);

	        // Récupère le sujet (nom d'utilisateur) à partir des claims du JWT
	        return (claims.getBody().getExpiration().after(Date.from(Instant.now())));
	}
	
	
}
