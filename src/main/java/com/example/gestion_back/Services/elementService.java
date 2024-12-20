package com.example.gestion_back.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Repository.elementRepo;
import com.example.gestion_back.Repository.etudiantRepo;
import com.example.gestion_back.Repository.filierRepo;

@Service
public class elementService {
	
	@Autowired
	etudiantRepo etudiantrepo;
	
	@Autowired
	elementRepo elementrepo;
	
	
	// Ajout de Element
	public String saveElement(Element element)  {
		elementrepo.save(element);
		return "succes";
	}
		
	// update Element
	public String updateElement(Long id, Element element){
			

		    Optional<Element> el= elementrepo.findById(id);
			
			if(el.isPresent()) {
				Element toupdate=el.get();
				toupdate.setNom(toupdate.getNom());
				toupdate.setCoefficient(element.getCoefficient());
				
				
				elementrepo.save(toupdate);
				return "succes";
			}
			
			return "failed";
		}
	
	// find modeule pa Element
	public Element findElement(Long id) {
		
		Optional<Element> el= elementrepo.findById(id);
		
		if(el.isPresent()) {
			Element elem=el.get();
			return elem;
		}
		
		return null;
	}
	
	// find all Elements
	public List<Element> allElements(){
		return elementrepo.findAll();
	}
	
	
	// delete Element
	public String deleteElement(Long id) {
		
		Optional<Element> el= elementrepo.findById(id);
		
		if(el.isPresent()) {
			Element ell=el.get();
			elementrepo.delete(ell);
			return "succes";
		}
		
		return "failed";
	}

}
