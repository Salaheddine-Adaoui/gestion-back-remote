package com.example.gestion_back.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.gestion_back.Dto.ElementsProfDto;
import com.example.gestion_back.Dto.PiechartProfDto;
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
import com.example.gestion_back.Repository.profRepo;
import com.example.gestion_back.Repository.userRepo;
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
	profRepo profrepo;
	
	@Autowired
	CustomUserDetailsService serv;
	
	@Autowired
	jwtUtils jwt_serv;
	
	@Autowired
	userRepo userrepo;
	
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
					    	Map<String,Object> map=new HashMap();
					    	map.put("token", jt);
					    	map.put("Email", jwt_serv.extractUsername(jt));
					    	map.put("Role", jwt_serv.extractRole(jt));
					    	Optional<Compte> compte=userrepo.findByEmail(jwt_serv.extractUsername(jt));
					    	if(compte.isPresent()) {
					    		Compte c=compte.get();
					    		Professeur prof=c.getProf();
					    		if(prof!=null) {
					    			map.put("enabled", prof.isEnabled());
					    		}else {
					    			map.put("enabled", true);
					    		}
					    		
					    	}
					    	return ResponseEntity.ok(map);
	    
		   }catch (BadCredentialsException ex) {
	            return ResponseEntity.status(401).body(Map.of(
	                    "success", false,
	                    "error", "Invalid Email or Password"
	                ));
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                return ResponseEntity.status(500).body(Map.of(
	                    "success", false,
	                    "error", "An internal server error occurred"
	                ));
	            }
	}
	
	@GetMapping("confirm")
    public ResponseEntity<String> confirmUser(@RequestParam("token") String token) {
        profserv.confirmUser(token);
        return ResponseEntity.ok("User confirmed successfully.");
    }
	
	@PostMapping("newadmin")
	ResponseEntity<String>  addAdmin(@ModelAttribute adminDto u) throws IOException {
		String issaved=adminserv.saveAdmin(u);
		if(issaved.equals("succes")) {
			return ResponseEntity.ok().body("Register with success");
		}else {
			return ResponseEntity.badRequest().body("this Email is already exist");
		}
	}
	
	@PostMapping("newprof")
	ResponseEntity<String>  addProf(@ModelAttribute profDto u) throws IOException {
		String issaved=profserv.saveProf(u);
		if(issaved.equals("succes")) {
			return ResponseEntity.ok().body("Register with success");
		}else {
			if(issaved.equals("err-em")) {
				return ResponseEntity.badRequest().body("this Email is already exist");
			}
			return ResponseEntity.badRequest().body("this Code already exist");
		}
	}
	
	@DeleteMapping("deleteprof/{code}")
	ResponseEntity<String>  deleteProf(@PathVariable String code) {
		String issaved=profserv.deleteProf(code);
		if(issaved.equals("succes")) {
			return ResponseEntity.ok().body("deleted with success");
		}else {
			return ResponseEntity.badRequest().body("delete failed");
		}
	}
	@PutMapping("updateprof/{code}")
	ResponseEntity<String>  updateProf(@PathVariable String code,@ModelAttribute profDto prof) throws IOException {
		String issaved=profserv.updateProf(code,prof);
		if(issaved.equals("succes")) {
			return ResponseEntity.ok().body("updated with success");
		}else {
			return ResponseEntity.badRequest().body("update failed");
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
	public ResponseEntity<Map<String,Object>> getCuurentprof(@PathVariable String email){
		Map<String,Object> prof=userserv.findProflogin(email);
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
	
	@GetMapping("elprofs/{code}")
	List<ElementsProfDto> tesst(@PathVariable String code) {
		return profrepo.getElementProf(code);
	}
	
	@GetMapping("piecharprof/{code}")
	List<PiechartProfDto > piecharprof(@PathVariable String code) {
		return profrepo.profpychart(code);
	}
	
	//----------- countprof T_T ----------
	@GetMapping("nbrprof")
    public ResponseEntity<Long> getNbProf() {
        Long count = profrepo.nbProf();
        return ResponseEntity.ok(count);
    }
	
	// update password de compte
	@PostMapping("user/updatepassword")
	ResponseEntity<String>  updatePasswordAdmin(@RequestParam("id") Long id,@RequestParam("password") String pass) {
		String res=adminserv.updatePasswordAdmin(id, pass);
		if(res.equals("succes")) {
			return ResponseEntity.ok().body("Password updated with success");
		}else {
			return ResponseEntity.badRequest().body("Compte not found");
		}
	}
	
	@PostMapping("user/updateImage")
	ResponseEntity<String>  updateImageAdmin(@RequestParam("id") Long id,@RequestBody MultipartFile image) throws IOException {
		String res=adminserv.addImage(id, image);
		if(res.equals("succes")) {
			return ResponseEntity.ok().body("profile image updated with success");
		}else {
			return ResponseEntity.badRequest().body("Compte not found");
		}
	}
	
	
	
	
}
