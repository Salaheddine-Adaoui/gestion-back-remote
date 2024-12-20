package com.example.gestion_back.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Repository.etudiantRepo;
import com.example.gestion_back.Repository.filierRepo;

@Service
public class etudiantService {
	
	@Autowired
	etudiantRepo etudiantrepo;
	
	@Autowired
	filierRepo filierrepo;
	
	
	// Ajout de l'etudiant
	public String saveEtudiant(Etudiant etudiant)  {
		etudiantrepo.save(etudiant);
		return "succes";
	}
		
	// update module
	public String updateEtudiant(String code ,Etudiant module){
			
		    Optional<Etudiant> m= etudiantrepo.findByCin(code);
		    Optional<Filier> f= filierrepo.findById(module.getFilier().getId());
			
			if(m.isPresent()&&f.isPresent()) {
				Etudiant toupdate=m.get();
				Filier ff=f.get();
				toupdate.setCin(module.getCin());
				toupdate.setNom(module.getNom());
				toupdate.setPrenom(module.getNom());
				toupdate.setFilier(ff);
				
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
	
	// find all modules
	public List<Etudiant> allEtudiants(){
		return etudiantrepo.findAll();
	}
	
	
	// delete module
	public String deleteAdmin(String cin) {
		
		Optional<Etudiant> et=etudiantrepo.findByCin(cin);
		if(et.isPresent()) {
			Etudiant md=et.get();
			
			etudiantrepo.delete(md);
			return "succes";
		}
		return "failed";
	}

}
