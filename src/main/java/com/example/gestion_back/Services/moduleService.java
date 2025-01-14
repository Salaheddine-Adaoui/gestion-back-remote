package com.example.gestion_back.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.ModuleCountByFilierDto;
import com.example.gestion_back.Dto.ModuleFilierDto;
import com.example.gestion_back.Dto.moduleDto;
import com.example.gestion_back.Dto.updateModFilDto;
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
	
	public Map<String,Object> getModuleFilier(Long id, String code){
		Moduleee mod= modulerepo.findModuleWithFilier(code, id);
		Map<String,Object> map=new HashMap<>();
				map.put("fil", mod.getFilier());
				map.put("code",code);
				map.put("nomm", mod.getNom());
		return map;
	}
	
	
	
	

	/*public String updateModFil(Long id, String code, updateModFilDto objt) {
	    // Récupérer le module et le filier
	    Moduleee moduleee = modulerepo.findModuleWithFilier(code, id);
	    Optional<Filier> newFilierOpt = filierrepo.findById(objt.getFilierid());

	    if (moduleee != null && newFilierOpt.isPresent()) {
	        Filier newFilier = newFilierOpt.get();

	        // Trouver l'ancien filier associé (par ID ou autre critère)
	        Optional<Filier> oldFilierOpt = moduleee.getFilier().stream()
	            .filter(filier -> filier.getId().equals(id)) // Remplacez "id" par l'identifiant à comparer
	            .findFirst();

	        if (oldFilierOpt.isPresent()) {
	            Filier oldFilier = oldFilierOpt.get();

	            // Remplacer l'ancien filier par le nouveau
	            moduleee.getFilier().remove(oldFilier);
	            moduleee.getFilier().add(newFilier);
	            moduleee.setNom(objt.getModuleNom()); // Mettre à jour d'autres champs si nécessaire

	            modulerepo.save(moduleee); // Sauvegarder les modifications
	            return "success";
	        }

	        return "failed: Old filier not found";
	    }

	    return "failed: Module or new filier not found";
	}*/

	

	
	/*public String assignerModulToFilier(Long id,String code) {
		
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
		
	}*/
	
	/*public String dletefilierfrommodule(Long id,String code) {
		Moduleee module = modulerepo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Module introuvable"));
        Filier filier = filierrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

            module.getFilier().remove(filier);
  
            filier.getModules().remove(module);

            modulerepo.save(module);
            filierrepo.save(filier);
            return "succes";

	}*/
	
	public String assignerModulToFilier(Long id,String code) {
		Moduleee module = modulerepo.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Module introuvable"));
        Filier filier = filierrepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
        
        module.setFilier(filier);
        modulerepo.save(module);

		return "succes";
	}
		
	// update module
	public String updateModule(String code ,moduleDto mm){
			
		    Optional<Moduleee> m= modulerepo.findByCode(code);
			
			if(m.isPresent()) {
				Moduleee toupdate=m.get();
			
				toupdate.setNom(mm.getNom());
				
				modulerepo.save(toupdate);
				return "succes";
			}
			
			return "failed";
		}
	
	// find modeule pa code
	public Map<String,String> findModule(String code) {
		
		Optional<Moduleee> module=modulerepo.findByCode(code);
		if(module.isPresent()) {
			Moduleee md=module.get();
			Map<String,String> map=new HashMap<>();
			map.put("code",md.getCode());
			map.put("nom", md.getNom());		
			return map;
		}
		return null;
	}
	
	//les module avec leur filier 
	public List<ModuleFilierDto> findModuleFilier(){
		return contenirrepo.findModuleFilier();
	}
	
	// find all modules
	public List<moduleDto> allModule(){
		List<Moduleee> list=modulerepo.findAll();
		return list.stream().map(
				mod->new moduleDto(mod.getCode(),mod.getNom())).collect(Collectors.toList());
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
	
	
	
	
	// =============== Module Group By Filier ===================
	public Map<String, List<ModuleCountByFilierDto>> getModulesGroupedByFiliere() {
        List<ModuleCountByFilierDto> modules = contenirrepo.findModuleCountByFilier();

        // Create a map to group by filiere
        Map<String, List<ModuleCountByFilierDto>> groupedModules = new HashMap<>();

        for (ModuleCountByFilierDto module : modules) {
            // Split filierNiveau into filiere and niveau
            String[] parts = module.getFilierNiveauNom().split(" ");
            String filiere = parts[0];

            // Add to the grouped map
            groupedModules.computeIfAbsent(filiere, k -> new ArrayList<>()).add(module);
        }

        return groupedModules;
    }
	
}
