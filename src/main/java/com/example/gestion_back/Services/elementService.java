package com.example.gestion_back.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.elementDto;
import com.example.gestion_back.Dto.etudiantDto;
import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Entities.Moduleee;
import com.example.gestion_back.Entities.Professeur;
import com.example.gestion_back.Repository.elementRepo;
import com.example.gestion_back.Repository.etudiantRepo;
import com.example.gestion_back.Repository.etudiantelementRepo;
import com.example.gestion_back.Repository.filierRepo;
import com.example.gestion_back.Repository.moduleRepo;
import com.example.gestion_back.Repository.profRepo;

@Service
public class elementService {
	
	@Autowired
	etudiantRepo etudiantrepo;
	
	@Autowired
	elementRepo elementrepo;
	
	@Autowired
	profRepo profrepo;
	
	@Autowired
	moduleRepo modulerepo;
	
	@Autowired
	etudiantelementRepo etelrepo;
	
	
	
	// Ajout de Element
	public String saveElement(elementDto el)  {
		
		//Professeur pr=profrepo.findByCode(el.getProfcode())
				//.orElseThrow(()-> new RuntimeException("prof not found "));
		
		Moduleee md=modulerepo.findByCode(el.getModulecode())
				.orElseThrow(()-> new RuntimeException("module not found "));
		Optional<Professeur> prof = profrepo.findByCode(el.getProfcode());
		Element elem=new Element();
		if(prof.isPresent()) {
			Professeur pr=prof.get();
			elem.setCoefficient(el.getCoiff());
			elem.setModule(md);
			elem.setNom(el.getNom());
			elem.setProf(pr);
			elementrepo.save(elem);
			return "succes";
		}
		
		
		elem.setCoefficient(el.getCoiff());
		elem.setModule(md);
		elem.setNom(el.getNom());
		elementrepo.save(elem);
		return "succes";
	}
	
	// assigner prof
	public String asignerProf(String code,Long id) {
		Professeur pr=profrepo.findByCode(code)
				.orElseThrow(()-> new RuntimeException("prof not found "));
		Element el=elementrepo.findById(id)
				.orElseThrow(()-> new RuntimeException("element not found"));
		
		el.setProf(pr);
		elementrepo.save(el);
		return "succes";
	}
	
	
	
		
	
	
	
	// update Element
	public String updateElement(Long id, elementDto ell){
		
		    Professeur pr=profrepo.findByCode(ell.getProfcode())
				.orElseThrow(()-> new RuntimeException("prof not found "));
		
		    Moduleee md=modulerepo.findByCode(ell.getModulecode())
				.orElseThrow(()-> new RuntimeException("module not found "));

		    Optional<Element> el= elementrepo.findById(id);
			
			if(el.isPresent()) {
				Element toupdate=el.get();
				toupdate.setNom(ell.getNom());
				toupdate.setCoefficient(ell.getCoiff());
				toupdate.setModule(md);
				toupdate.setProf(pr);
				
				
				elementrepo.save(toupdate);
				return "succes";
			}
			
			return "failed";
		}
	
	// find modeule pa Element
	public Map<String,Object> findElement(Long id) {
		
		Optional<Element> ii= elementrepo.findById(id);
		
		if (ii.isPresent()) {
			Element i=ii.get();
			Map<String,Object> map=new HashMap<>();
	    	//map.put("nom", i.getId());
	    	map.put("nom", i.getNom());
	    	map.put("coiff", i.getCoefficient());
	    	map.put("modulecode",i.getModule()!=null? i.getModule().getCode():"");
	    	map.put("profcode",i.getProf()!=null? i.getProf().getCode():"");
	    	//map.put("profprenom",i.getProf()!=null? i.getProf().getPrenom():"");
	    	return map;
		}
		return null;
		
		
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
	
	// find all Element
	public List<Map<String,Object>> allElements() {
		
	    List<Element> elem = elementrepo.findAll();
	    List<Map<String,Object>> list=new ArrayList<>();
	   
	    for (Element i :elem) {
	    	Map<String,Object> map=new HashMap<>();
	    	map.put("elementid", i.getId());
	    	map.put("elementnom", i.getNom());
	    	map.put("elementcoif", i.getCoefficient());
	    	map.put("moduleNom",i.getModule()!=null? i.getModule().getNom():"");
	    	map.put("profNom",i.getProf()!=null? i.getProf().getNom():"");
	    	map.put("profprenom",i.getProf()!=null? i.getProf().getPrenom():"");
	    	list.add(map);
	    }
	    return list;
	    
	}
	
	public List<Map<String,Object>> getAllValidByID(String code) {
		
		List<elementDto> elements=etelrepo.elementvalid(code);
		List<Map<String,Object>> list=new ArrayList<>();
		for(elementDto i:elements) {
			Map<String,Object> map=new HashMap<>();
			Long all=etelrepo.CountAll(i.getId());
			Long allvalid=etelrepo.isAllValid(i.getId());
			if(all==allvalid) {
				map.put("id", i.getId());
				map.put("nom", i.getNom());
				map.put("status",i.getModulecode());
				list.add(map);
			}
		}
		 List<Map<String, Object>> distinctMaps = new ArrayList<>();
	        Set<Object> seenIds = new HashSet<>();

	        // Parcourir la liste avec une boucle
	        for (Map<String, Object> map : list) {
	            Object id = map.get("id");
	            if (!seenIds.contains(id)) {
	                distinctMaps.add(map);
	                seenIds.add(id);
	        }
	     }
		return  distinctMaps;
	}

}
