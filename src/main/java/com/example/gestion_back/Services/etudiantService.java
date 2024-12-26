package com.example.gestion_back.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.etudiantDto;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Entities.Moduleee;
import com.example.gestion_back.Repository.etudiantRepo;
import com.example.gestion_back.Repository.filierRepo;

@Service
public class etudiantService {
	
	@Autowired
	etudiantRepo etudiantrepo;
	
	@Autowired
	filierRepo filierrepo;
	
	
	// Ajout de l'etudiant
	public String saveEtudiant(etudiantDto etudiant)  {
		Etudiant e = new Etudiant();
		e.setCin(etudiant.getCin());
		e.setNom(etudiant.getNom());
		e.setPrenom(etudiant.getPrenom());
		e.setEmail(etudiant.getEmail());
		e.setTelephone(etudiant.getTelephone());
		
		etudiantrepo.save(e);
		
		return "succes";
	}
		
	// update module
	public String updateEtudiant(String code ,Etudiant etudiant){
			
		    Optional<Etudiant> e= etudiantrepo.findByCin(code);
			
			if(e.isPresent()) {
				Etudiant toupdate = e .get();
				
				toupdate.setCin(etudiant.getCin());
				toupdate.setNom(etudiant.getNom());
				toupdate.setPrenom(etudiant.getPrenom());
				toupdate.setEmail(etudiant.getEmail());
				toupdate.setTelephone(etudiant.getTelephone());
				

				
				etudiantrepo.save(toupdate);
				return "succes";
			}
			
			return "failed";
		}
	
	
	
	
	// find modeule pa code
	public Etudiant findEtudiant(String cin) {
		
		Optional<Etudiant> et=etudiantrepo.findByCin(cin);
		if(et.isPresent()) {
			Etudiant md=et.get();
			
			return md;
		}
		return null;
	}
	
	// find all Etudiant
	public List<Etudiant> allEtudiants(){
		return etudiantrepo.findAll();
	}
	
	
	// delete module
	public String deleteEtudiant(String cin) {
		
		Optional<Etudiant> et=etudiantrepo.findByCin(cin);
		if(et.isPresent()) {
			Etudiant md = et.get();
			
			etudiantrepo.delete(md);
			return "succes";
		}
		return "failed";
	}

}
