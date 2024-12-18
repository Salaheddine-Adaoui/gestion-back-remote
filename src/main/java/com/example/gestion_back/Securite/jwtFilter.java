package com.example.gestion_back.Securite;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class jwtFilter extends OncePerRequestFilter{
	
	@Autowired
	jwtUtils jwt_serv;
	
	@Autowired
	CustomUserDetailsService serv;
    
	@Override
	protected void doFilterInternal(@SuppressWarnings("null") @NonNull HttpServletRequest request,@SuppressWarnings("null") @NonNull HttpServletResponse response,@SuppressWarnings("null") @NonNull FilterChain filterChain)
			throws ServletException, IOException {
		String head=request.getHeader("authorization");
		if(head==null || !head.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		else {
			String myjwt=head.substring(7);
			String username=jwt_serv.extractUsername(myjwt);
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				CustomUserDetails detail=serv.loadUserByUsername(username);
				if(detail!=null && jwt_serv.isvalid(myjwt)) {
					UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(detail.getUsername(),detail.getPassword(),detail.getAuthorities());
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth);
				}
			}
		}
		filterChain.doFilter(request, response);
		
	}
	
}
