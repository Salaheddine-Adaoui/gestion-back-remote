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

import com.example.gestion_back.Dto.profDto;
import com.example.gestion_back.Entities.Admin;
import com.example.gestion_back.Entities.Compte;
import com.example.gestion_back.Entities.Professeur;
import com.example.gestion_back.Repository.profRepo;
import com.example.gestion_back.Repository.userRepo;

@Service
public class profService {
	
	@Autowired
	userRepo userrepo;
	
	@Autowired
	profRepo profrepo;
	
	@Autowired
	userRepo compterepo ;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncod;
	
	
	// creation de prof avec creation auto de compte
	public String saveProf(profDto profdto) throws IOException {
		
		Compte c=new Compte();
		c.setEmail(profdto.getEmail());
		c.setPassword(bCryptPasswordEncod.encode(profdto.getPassword()));
		c.setImage(Base64.getEncoder().encodeToString(profdto.getFile().getBytes()));
		c.setRole("PROF");
		
		Compte saved=userrepo.save(c);
		
		Professeur prof=new Professeur();
		prof.setCode(profdto.getCode());
		prof.setNom(profdto.getNom());
		prof.setPrenom(profdto.getPrenom());
		prof.setSpecialite(profdto.getSpecialite());
		prof.setCompte(c);
		
		profrepo.save(prof);
		
		return "succes";
	}
	
	
	public String updateProf(String code,profDto profdto) throws IOException {
		
		Optional<Professeur> prof=profrepo.findByCode(code);
		
		if(prof.isPresent()) {
			Professeur toupdate=prof.get();
			toupdate.setCode(profdto.getCode());
			toupdate.setNom(profdto.getNom());
			toupdate.setPrenom(profdto.getPrenom());
			toupdate.setSpecialite(profdto.getSpecialite());
			
			Compte c=toupdate.getCompte();
			c.setEmail(profdto.getEmail());
			c.setPassword(bCryptPasswordEncod.encode(profdto.getPassword()));
			c.setImage(Base64.getEncoder().encodeToString(profdto.getFile().getBytes()));
			c.setRole("PROF");
			
			Compte compteupdated=compterepo.save(c);
			
			toupdate.setCompte(compteupdated);
			
			profrepo.save(toupdate);
			
			return "succes";
		}
		
		return "failed";
	}
	
	
	
	public String deleteProf(String code) {
		
        Optional<Professeur> prof=profrepo.findByCode(code);
		if(prof.isPresent()) {
			Professeur todelete=prof.get();
			profrepo.delete(todelete);
			return "succes";
		}
		return "failed";
	}
	
	public Map<String,Object> findAdmin(String code) {
		HashMap<String, Object> map=new HashMap<>();
		Optional<Professeur> prof=profrepo.findById(code);
		if(prof.isPresent()) {
			Professeur ad=prof.get();
			Compte c=ad.getCompte();
			
			
			map.put("prof",ad);
			map.put("compte", c);
			return map;
		}
		map.put("error", "ce code n'exist pas");
		return map;
	}
	
	public List<Professeur> allProf(){
		return profrepo.findAll();
	}
}
