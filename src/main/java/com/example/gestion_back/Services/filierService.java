package com.example.gestion_back.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.filierDto;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Repository.filierRepo;

@Service
public class filierService {
	
	@Autowired
	filierRepo filierrepo;
	
	public String saveFilier(filierDto d) {
		Filier ff=new Filier();
		ff.setDepartement(d.getDepartement());
		ff.setNiveau(d.getNiveau());
		ff.setNom(d.getNom());
		filierrepo.save(ff);
		return "succes";
	}
	
	
	
      
	
	
}
