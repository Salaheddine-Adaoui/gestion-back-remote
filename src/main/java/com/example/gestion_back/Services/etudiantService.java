package com.example.gestion_back.Services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.etudiantDto;
import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.EtudiantElement;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Entities.Moduleee;
import com.example.gestion_back.Repository.elementRepo;
import com.example.gestion_back.Repository.etudiantRepo;
import com.example.gestion_back.Repository.etudiantelementRepo;
import com.example.gestion_back.Repository.filierRepo;
import com.example.gestion_back.Repository.moduleRepo;

@Service
public class etudiantService {
	
	@Autowired
	etudiantRepo etudiantrepo;
	
	@Autowired
	etudiantelementRepo etudiantelementrepo;
	
	@Autowired
	filierRepo filierrepo;
	
	@Autowired
	moduleRepo modulerepo;
	
	@Autowired
	elementRepo elementrepo;
	
	
	
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
	public List<etudiantDto> getAllEtudiants() {
	    List<Etudiant> etudiants = etudiantrepo.findAll();
	    return etudiants.stream()
	        .map(etudiant -> new etudiantDto(
	            etudiant.getCin(),
	            etudiant.getNom(),
	            etudiant.getPrenom(),
	            etudiant.getEmail(),
	            etudiant.getTelephone(),
	            etudiant.getFilier() != null ? etudiant.getFilier().getId() : null,
	            etudiant.getFilier() !=null ? etudiant.getFilier().getNom() : null
	        ))
	        .collect(Collectors.toList());
	}
	
	// asigner filier to etudiant
		public String assignerFilToEtudiant(String cin,Long id) {
			Etudiant et=etudiantrepo.findByCin(cin)
					.orElseThrow(() -> new RuntimeException("etudiant introuvable"));
			Filier fil=filierrepo.findById(id)
					.orElseThrow(() -> new RuntimeException("filier introuvable"));
			
			List<Moduleee> m=fil.getModules();
			for(Moduleee mod:m) {
				List<Element> el=mod.getElements();
				for(Element e:el) {
					EtudiantElement etel=new EtudiantElement();
					etel.setEtudiant(et);
					etel.setElement(e);
					etel.setStatus("pending");
					etudiantelementrepo.save(etel);
				}
			}
			
			et.setFilier(fil);
			etudiantrepo.save(et);
			return "succes";
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
