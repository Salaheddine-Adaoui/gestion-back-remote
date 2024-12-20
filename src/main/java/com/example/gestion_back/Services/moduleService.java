package com.example.gestion_back.Services;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.gestion_back.Entities.Modulee;
import com.example.gestion_back.Repository.moduleRepo;


@Service
public class moduleService {
	
	@Autowired
	moduleRepo modulerepo;
	
	
	// creation de module
	public String saveModule(Modulee module)  {
		modulerepo.save(module);
		return "succes";
	}
		
	// update module
	public String updateModule(String code ,Modulee module){
			
		    Optional<Modulee> m= modulerepo.findByCode(code);
			
			if(m.isPresent()) {
				Modulee toupdate=m.get();
				toupdate.setCode(module.getCode());
				toupdate.setNom(module.getNom());
				
				modulerepo.save(toupdate);
				return "succes";
			}
			
			return "failed";
		}
	
	// find modeule pa code
	public Modulee findModule(String code) {
		
		Optional<Modulee> module=modulerepo.findByCode(code);
		if(module.isPresent()) {
			Modulee md=module.get();
			
			return md;
		}
		return null;
	}
	
	// find all modules
	public List<Modulee> allModule(){
		return modulerepo.findAll();
	}
	
	
	// delete module
	public String deleteModule(String code) {
		
		Optional<Modulee> module=modulerepo.findByCode(code);
		if(module.isPresent()) {
			Modulee md=module.get();
			
			modulerepo.delete(md);
			return "succes";
		}
		return "failed";
	}

}
