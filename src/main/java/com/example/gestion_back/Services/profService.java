package com.example.gestion_back.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.gestion_back.Dto.profDto;
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
	userServices userserv;
	
	@Autowired
	userRepo compterepo ;
	
	@Autowired
	private PasswordEncoder bCryptPasswordEncod;
	
	
	// creation de prof avec creation auto de compte
	public String saveProf(profDto profdto) throws IOException {
		
		boolean iscodeexist=profrepo.CodeDejaExist(profdto.getCode());
		if(iscodeexist) {
			return "err-code";
		}
		
		
		
		String em=profdto.getEmail();
		
		Optional<Compte> cc=userrepo.findByEmail(em);
		if(cc.isPresent()) {
			return "err-em";
		}
		
		Compte c=new Compte();
		c.setEmail(profdto.getEmail());
		c.setPassword(bCryptPasswordEncod.encode(profdto.getPassword()));
		if (profdto.getFile() != null && !profdto.getFile().isEmpty()) {
		    c.setImage(Base64.getEncoder().encodeToString(profdto.getFile().getBytes()));
		} else {
		    c.setImage(null);
		}
		c.setRole("PROF");
		
		Compte saved=userrepo.save(c);
		
		Professeur prof=new Professeur();
		prof.setCode(profdto.getCode());
		prof.setNom(profdto.getNom());
		prof.setPrenom(profdto.getPrenom());
		prof.setSpecialite(profdto.getSpecialite());
		prof.setAdresse(profdto.getAdresse());
		prof.setTel(profdto.getTel());
		prof.setCompte(c);
		
		profrepo.save(prof);
		
		return "succes";
	}
	
	
	
	
	public String updateProf(String code,profDto profdto) throws IOException {
		String em=profdto.getEmail();
		boolean existautreprof=userrepo.existsByEmailAndNotCode(em, code);
		if(existautreprof) {
			return "failed";		
			}
		Optional<Professeur> prof=profrepo.findByCode(code);
		
		if(prof.isPresent()) {
            
			
			Professeur toupdate=prof.get();
			toupdate.setNom(profdto.getNom());
			toupdate.setPrenom(profdto.getPrenom());
			toupdate.setSpecialite(profdto.getSpecialite());
			toupdate.setTel(profdto.getTel());
			toupdate.setAdresse(profdto.getAdresse());
			toupdate.setAdresse(profdto.getAdresse());
			toupdate.setTel(profdto.getTel());
			
			Compte c=toupdate.getCompte();
			c.setEmail(profdto.getEmail());
			c.setPassword(bCryptPasswordEncod.encode(profdto.getPassword()));
			if (profdto.getFile() != null && !profdto.getFile().isEmpty()) {
			    c.setImage(Base64.getEncoder().encodeToString(profdto.getFile().getBytes()));
			} else {
			    c.setImage(null);
			}
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
	
	public Map<String,Object> findProf(String code) {
		HashMap<String, Object> map=new HashMap<>();
		Optional<Professeur> prof=profrepo.findByCode(code);
		if(prof.isPresent()) {
			Professeur ad=prof.get();
			Compte c=ad.getCompte();
	        
	        // Add Professeur data
	        map.put("code", ad.getCode());
	        map.put("nom", ad.getNom());
	        map.put("prenom", ad.getPrenom());
	        map.put("specialite", ad.getSpecialite());
	        map.put("tel", ad.getTel());
	        map.put("adresse", ad.getAdresse());
	        
	        // Add Compte data (with the account ID)
	        Compte compte = ad.getCompte();
	        map.put("email", compte.getEmail());
	        map.put("image", compte.getImage());
			return map;
		}
		map.put("error", "ce code n'exist pas");
		return map;
	}
	
	public List<Map<String, Object>> findAllProf() {
	    List<Map<String, Object>> resultList = new ArrayList<>();
	    List<Professeur> professors = profrepo.findAll(); // Fetch all professors
	    
	    for (Professeur prof : professors) {
	        HashMap<String, Object> map = new HashMap<>();
	        
	        // Add Professeur data
	        map.put("code", prof.getCode());
	        map.put("nom", prof.getNom());
	        map.put("prenom", prof.getPrenom());
	        map.put("specialite", prof.getSpecialite());
	        map.put("tel", prof.getTel());
	        map.put("adresse", prof.getAdresse());
	        
	        // Add Compte data (with the account ID)
	        Compte compte = prof.getCompte();
	        map.put("email", compte.getEmail());
	        map.put("image", compte.getImage());
	       
	        
	        resultList.add(map); // Add the map to the result list
	    }
	    
	    return resultList;
	}

}
