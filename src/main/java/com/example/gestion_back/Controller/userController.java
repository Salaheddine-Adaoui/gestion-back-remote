package com.example.gestion_back.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_back.Dto.adminDto;
import com.example.gestion_back.Dto.loginDto;
import com.example.gestion_back.Dto.profDto;
import com.example.gestion_back.Dto.userDto;
import com.example.gestion_back.Entities.Compte;
import com.example.gestion_back.Securite.CustomUserDetails;
import com.example.gestion_back.Securite.CustomUserDetailsService;
import com.example.gestion_back.Securite.jwtUtils;
import com.example.gestion_back.Services.adminService;
import com.example.gestion_back.Services.profService;
import com.example.gestion_back.Services.userServices;
import com.example.gestion_back.Entities.Professeur;
import com.example.gestion_back.Entities.Admin;





@RestController
public class userController {
	
	@Autowired
	userServices userserv;
	
	@Autowired
	adminService adminserv;
	
	@Autowired
	profService profserv;
	
	@Autowired
	CustomUserDetailsService serv;
	
	@Autowired
	jwtUtils jwt_serv;
	
	@Autowired
	AuthenticationManager authenticationMnager;
	
	@GetMapping("user/hello")
	String sayhello() {
		return "hello wolrd";
	}
	
	@PostMapping("/auth")
	ResponseEntity<Object> login(@RequestBody loginDto a){
		try {
			   Authentication auth=authenticationMnager.authenticate(new
					    UsernamePasswordAuthenticationToken(a.getEmail(),a.getPassword()));
				        
			            CustomUserDetails userDetailss = (CustomUserDetails) auth.getPrincipal();
					
					    	String jt= jwt_serv.generateToken(serv.loadUserByUsername(a.getEmail()));
					    	Map<String,String> map=new HashMap();
					    	map.put("token", jt);
					    	map.put("Email", jwt_serv.extractUsername(jt));
					    	map.put("Role", jwt_serv.extractRole(jt));
					    	return ResponseEntity.ok(map);
					    
					    
		   }catch (BadCredentialsException ex) {
	            return ResponseEntity.status(401).body(Map.of(
	                    "success", false,
	                    "error", "Invalid email or password"
	                ));
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                return ResponseEntity.status(500).body(Map.of(
	                    "success", false,
	                    "error", "An internal server error occurred"
	                ));
	            }
	}
	
	@PostMapping("newadmin")
	ResponseEntity<String>  addAdmin(@ModelAttribute adminDto u) throws IOException {
		String issaved=adminserv.saveAdmin(u);
		if(issaved.equals("succes")) {
			return ResponseEntity.ok().body("register whit succes");
		}else {
			return ResponseEntity.badRequest().body("this email is already exist");
		}
	}
	
	@PostMapping("newprof")
	ResponseEntity<String>  addProf(@ModelAttribute profDto u) throws IOException {
		String issaved=profserv.saveProf(u);
		if(issaved.equals("succes")) {
			return ResponseEntity.ok().body("register whit succes");
		}else {
			return ResponseEntity.badRequest().body("this email is already exist");
		}
	}
	
	@DeleteMapping("deleteprof/{code}")
	ResponseEntity<String>  deleteProf(@PathVariable String code) {
		String issaved=profserv.deleteProf(code);
		if(issaved.equals("succes")) {
			return ResponseEntity.ok().body("deleted whit succes");
		}else {
			return ResponseEntity.badRequest().body("error, delete failed");
		}
	}
	@PutMapping("updateprof/{code}")
	ResponseEntity<String>  updateProf(@PathVariable String code,@ModelAttribute profDto prof) throws IOException {
		String issaved=profserv.updateProf(code,prof);
		if(issaved.equals("succes")) {
			return ResponseEntity.ok().body("updated whit succes");
		}else {
			return ResponseEntity.badRequest().body("error, update failed");
		}
	}
	
	
	@GetMapping("user/allusers")
	public List<Compte> findall(){
		return userserv.findall();
	}
	@GetMapping("allprofs")
	public  List<Map<String, Object>> findallprofs(){
		return profserv.findAllProf();
	}
	@GetMapping("alladmins")
	public List<Admin> findalladmins(){
		return adminserv.allAdmins();
	}
	

	@GetMapping("getcurrentprof/{email}")
	public ResponseEntity<Map<String,String>> getCuurentprof(@PathVariable String email){
		Map<String,String> prof=userserv.findProflogin(email);
		if(prof.containsKey("error")) {
			return  ResponseEntity.badRequest().body(prof);
		}
		return ResponseEntity.ok(prof);
	}
	
	
	@GetMapping("getprof/{code}")
	public ResponseEntity<Map<String,Object>> getprof(@PathVariable String code){
		Map<String,Object> prof=profserv.findProf(code);
		if(prof.containsKey("error")) {
			return  ResponseEntity.badRequest().body(prof);
		}
		return ResponseEntity.ok(prof);
	}
	
	
	@GetMapping("test")
	String sayHello() {
		return "hello world";
	}
}
