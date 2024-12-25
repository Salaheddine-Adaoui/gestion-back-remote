package com.example.gestion_back.Services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.ModuleFilierDto;
import com.example.gestion_back.Dto.moduleDto;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Entities.Moduleee;
import com.example.gestion_back.Repository.contenirRepo;
import com.example.gestion_back.Repository.filierRepo;
import com.example.gestion_back.Repository.moduleRepo;


@Service
public class moduleService {
	
	@Autowired
	moduleRepo modulerepo;
	
	@Autowired
	contenirRepo contenirrepo;
	
	@Autowired
	filierRepo filierrepo;
	
	
	// creation de module
	public String saveModule(moduleDto module)  {
		Moduleee m=new Moduleee();
		m.setCode(module.getCode());
		m.setNom(module.getNom());
		modulerepo.save(m);
		return "succes";
	}
	
	public String assignerModulToFilier(Long id,String code) {
		
		 Moduleee module = modulerepo.findByCode(code)
	                .orElseThrow(() -> new RuntimeException("Module introuvable"));
	        Filier filier = filierrepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
		
	        if (!module.getFilier().contains(filier)) {
	           
	            module.getFilier().add(filier);

	        
	            filier.getModules().add(module);

	     
	            modulerepo.save(module);
	            filierrepo.save(filier);
	            return "succes";
	        }
		    
		    return "failed";
		
	}
	
	public String dletefilierfrommodule(Long id,String code) {
		Moduleee module = modulerepo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Module introuvable"));
        Filier filier = filierrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
	
        
           
            module.getFilier().remove(filier);

        
            filier.getModules().remove(module);

     
            modulerepo.save(module);
            filierrepo.save(filier);
            return "succes";
        
	    
	 
	}
		
	// update module
	public String updateModule(String code ,Moduleee module){
			
		    Optional<Moduleee> m= modulerepo.findByCode(code);
			
			if(m.isPresent()) {
				Moduleee toupdate=m.get();
				toupdate.setCode(module.getCode());
				toupdate.setNom(module.getNom());
				
				modulerepo.save(toupdate);
				return "succes";
			}
			
			return "failed";
		}
	
	// find modeule pa code
	public Moduleee findModule(String code) {
		
		Optional<Moduleee> module=modulerepo.findByCode(code);
		if(module.isPresent()) {
			Moduleee md=module.get();
			
			return md;
		}
		return null;
	}
	
	//les module avec leur filier 
	public List<ModuleFilierDto> findModuleFilier(){
		return contenirrepo.findModuleFilier();
	}
	
	// find all modules
	public List<Moduleee> allModule(){
		return modulerepo.findAll();
	}
	
	
	// delete module
	public String deleteModule(String code) {
		
		Optional<Moduleee> module=modulerepo.findByCode(code);
		if(module.isPresent()) {
			Moduleee md=module.get();
			
			modulerepo.delete(md);
			return "succes";
		}
		return "failed";
	}
}
