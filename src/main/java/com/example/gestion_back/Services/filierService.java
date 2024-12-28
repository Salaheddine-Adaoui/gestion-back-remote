package com.example.gestion_back.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.filierDto;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Repository.filierRepo;

@Service
public class filierService {
	
	@Autowired
	filierRepo filierrepo;
	
	public String saveFilier(filierDto fd) {
		Filier f=new Filier();
		f.setDepartement(fd.getDepartement());
		f.setNiveau(fd.getNiveau());
		f.setNom(fd.getNom());
		filierrepo.save(f);
		return "succes";
	}
	
	public String updatefiliere(Long id ,filierDto fd){
		
	    Optional<Filier> e= filierrepo.findById(id);
		
		if(e.isPresent()) {
			Filier toupdate = e .get();
			
			toupdate.setDepartement(fd.getDepartement());
			toupdate.setNiveau(fd.getNiveau());
			toupdate.setNom(fd.getNom());
			
			filierrepo.save(toupdate);
			return "succes";
		}
		
		return "failed";
	}
	
	public Filier findFiliere(Long id) {
		
		Optional<Filier> et = filierrepo.findById(id);
		if(et.isPresent()) {
			
			Filier md = et.get();
			
			return md;
		}
		return null;
	}
	
	
	// find all Etudiant
	public List<Filier> allFiliers(){
		return filierrepo.findAll();
	}
	
	
	public String deleteFilier(Long id) {
		
		Optional<Filier> et=filierrepo.findById(id);
		if(et.isPresent()) {
			
			Filier  md = et.get();
			
			filierrepo.delete(md);
			
			return "succes";
		}
		return "failed";
	}
	
	
	
      
	
	
}
