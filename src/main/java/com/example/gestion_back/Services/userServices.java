package com.example.gestion_back.Services;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.userDto;
import com.example.gestion_back.Entities.Admin;
import com.example.gestion_back.Entities.Compte;
import com.example.gestion_back.Entities.Professeur;
import com.example.gestion_back.Repository.userRepo;

@Service
public class userServices {
	
	@Autowired
	userRepo userrepo;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncod;
	
    // register new user
	public boolean saveUser(userDto u) throws IOException {
		Optional<Compte> user=userrepo.findByEmail(u.getEmail());
		if(user.isPresent()) {
			return false;
		}
		Compte saved=new Compte();
		saved.setEmail(u.getEmail());
		saved.setPassword(bCryptPasswordEncod.encode(u.getPassword()));
		saved.setImage(Base64.getEncoder().encodeToString(u.getFile().getBytes()));
		saved.setRole("PROF");
		userrepo.save(saved);
		return true;
	}
	
	public List<Compte> findall() {
		List<Compte> alluser=userrepo.findAll();
		return alluser;
	}
	
	public Map<String,String> findProflogin(String email){
		Map<String,String> map=new HashMap<>();
		Compte compte=findcurrentLogin(email);
		if(compte!=null) {
			Professeur prof=compte.getProf();
			if(prof==null) {
				Admin admin=compte.getAdmin();
				map.put("email", compte.getEmail());
				map.put("Role", compte.getRole());
				map.put("nom", admin.getNom());
				map.put("prenom", admin.getPrenom());
				map.put("image", compte.getImage());
				return map;
			}
			map.put("email", compte.getEmail());
			map.put("Role", compte.getRole());
			map.put("nom", prof.getNom());
			map.put("code", prof.getCode());
			map.put("prenom", prof.getPrenom());
			map.put("specialite",prof.getSpecialite() );
			map.put("image", compte.getImage());
			map.put("tel", prof.getTel());
			map.put("adresse", prof.getAdresse());
			return map;
		}
		map.put("error", "pas de current user");
		return map;
	}
	
	public Compte findcurrentLogin(String email) {
		Optional<Compte> c=userrepo.findByEmail(email);
		if(c.isPresent()) {
			Compte cc=c.get();
			return cc;
		}
		return null;
	}
	
	/* update user
	public String updateuser(Long id,userUpdateDto u) {
		Optional<myUser> myuserbyemail=userrepo.findByEmail(u.getEmail());
		Optional<myUser> myuser=userrepo.findById(id);
		if(myuserbyemail.isPresent()&&myuser.isPresent()&&!u.getEmail().equals(myuser.get().getEmail())) {
			return "duplication";
		}
		else{
			if(myuser.isPresent()) {
		
			
			myUser user=myuser.get();
			user.setEmail(u.getEmail());
			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setRole(u.getRole());
			
			myUser saved=userrepo.save(user);
			return "succes";
			
		}
		return "not found";
		}
		
	}
	*/
	
	// find by id
	public Compte finduserbyid(Long id) {
		Optional<Compte> myuser=userrepo.findById(id);
		if(myuser.isPresent()) {
			Compte a=myuser.get();
			return a;
		}
		return null;
	}
	
	// delete user
	public String deletebyd(Long id) {
		Optional<Compte> myuser=userrepo.findById(id);
		if(myuser.isPresent()) {
			Compte a=myuser.get();
			userrepo.deleteById(id);
			return "succes";
		}
		return "error";
	}
}
