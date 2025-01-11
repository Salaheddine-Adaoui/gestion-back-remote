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
import org.springframework.web.multipart.MultipartFile;

import com.example.gestion_back.Dto.adminDto;
import com.example.gestion_back.Dto.profDto;
import com.example.gestion_back.Entities.Admin;
import com.example.gestion_back.Entities.Compte;
import com.example.gestion_back.Entities.Professeur;
import com.example.gestion_back.Repository.adminRepo;
import com.example.gestion_back.Repository.userRepo;

@Service
public class adminService {

	
	@Autowired
	userRepo compterepo;
	
	@Autowired
	adminRepo adminrepo;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncod;
	
	
	// creation de admin avec creation auto de compte 
	public String saveAdmin(adminDto admindto) throws IOException {
		
		
		
		Compte c=new Compte();
		c.setEmail(admindto.getEmail());
		c.setPassword(bCryptPasswordEncod.encode(admindto.getPassword()));
		c.setImage(Base64.getEncoder().encodeToString(admindto.getFile().getBytes()));
		c.setRole("ADMIN");
		
		Compte saved=compterepo.save(c);
		
		Admin admin=new Admin();
		
		admin.setNom(admindto.getNom());
		admin.setPrenom(admindto.getPrenom());
		admin.setCompte(c);
		
		adminrepo.save(admin);
		
		return "succes";
	}
		
	// update admin 
	public String updateAdmin(Long id,adminDto admindto) throws IOException {
			
		    Optional<Admin> admin=adminrepo.findById(id);
			
			if(admin.isPresent()) {
				Admin toupdate=admin.get();
				toupdate.setNom(admindto.getNom());
				toupdate.setPrenom(admindto.getPrenom());
				
				Compte c=toupdate.getCompte();
				c.setEmail(admindto.getEmail());
				c.setRole("ADMIN");
				Compte compteupdated=compterepo.save(c);
				toupdate.setCompte(compteupdated);
				adminrepo.save(toupdate);
				
				return "succes";
			}
			
			return "failed";
		}
	
	// find admin by id 
	public Map<String,Object> findAdmin(Long id) {
		HashMap<String, Object> map=new HashMap<>();
		Optional<Admin> prof=adminrepo.findById(id);
		if(prof.isPresent()) {
			Admin ad=prof.get();
			Compte c=ad.getCompte();
	        
	        map.put("code", ad.getId());
	        map.put("nom", ad.getNom());
	        map.put("prenom", ad.getPrenom());
	        
	        Compte compte = ad.getCompte();
	        map.put("email", compte.getEmail());
	        map.put("image", compte.getImage());
			return map;
		}
		map.put("error", "ce code n'exist pas");
		return map;
	}
	
	// find all admins
	public List<Admin> allAdmins(){
		return adminrepo.findAll();
	}
	
	
	// delete admin
	public String deleteAdmin(Long id) {
		
        Optional<Admin> admin=adminrepo.findById(id);
		if(admin.isPresent()) {
			Admin todelete=admin.get();
			adminrepo.delete(todelete);
			return "succes";
		}
		return "failed";
	}
	
	public String updatePasswordAdmin(Long id,String password) {
		
		Optional<Admin> admin=adminrepo.findById(id);
		if(admin.isPresent()) {
			Admin toupdate=admin.get();
			Compte c=toupdate.getCompte();
			if(password.length()>0) {
				c.setPassword(bCryptPasswordEncod.encode(password));
			}
			compterepo.save(c);
			return "succes";
		}
		return "failed";
	}
	
	public String addImage(Long id,MultipartFile file) throws IOException {
		
		Optional<Admin> admin=adminrepo.findById(id);
		if(admin.isPresent()) {
			Admin toupdate=admin.get();
			Compte c=toupdate.getCompte();
			c.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			compterepo.save(c);
			return "succes";
		}
		return "failed";
		
	}
	
}
