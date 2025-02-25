package com.example.gestion_back.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_back.Dto.EtudiantPerFilierChart;
import com.example.gestion_back.Dto.etudiantDto;
import com.example.gestion_back.Dto.filierDto;
import com.example.gestion_back.Dto.moduleDto;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Repository.filierRepo;
import com.example.gestion_back.Services.filierService;
import com.example.gestion_back.Services.moduleService;


@RestController
@RequestMapping("filier")
public class filierController {
   
	@Autowired
	filierService filierserv;
	
	@Autowired
	filierRepo filierrepo;
	
	
	//=================================================//
	@GetMapping("filiertudiants/{id}")
	public ResponseEntity<Object> filierEtudiants(@PathVariable Long id) {
	    List<etudiantDto> etudiants = filierrepo.getFilierEtudiants(id);
	    if (etudiants != null && !etudiants.isEmpty()) {
	        return ResponseEntity.ok(etudiants); 
	    }
	    return ResponseEntity.badRequest().body("No students found for the given Filiere ID");
	}
	//=================================================//
	
	
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addFilier(@RequestBody filierDto m){
		
		String res=filierserv.saveFilier(m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Filier added with success");
		}
		return ResponseEntity.badRequest().body("Filier not added");

	}
	
	@GetMapping("allFiliers")
	public ResponseEntity<List<Map<String,Object>>> findall(){
		return ResponseEntity.ok(filierserv.allFiliers());
	}
	
	@GetMapping("id/{id}")
	public ResponseEntity<Object> findbuid(@PathVariable Long id){
		Filier e = filierserv.findFiliere(id);
		if(e!=null) {
			return ResponseEntity.ok(e);
		}
		return ResponseEntity.badRequest().body("Filier not found");
	}
	
	@DeleteMapping("deleteFiliere/{id}")
	public ResponseEntity<String> deletebyid(@PathVariable Long id){
		String mes= filierserv.deleteFilier(id);
		if(mes.equals("succes")) {
			return ResponseEntity.ok("Filier deleted with success");
		}
		return ResponseEntity.badRequest().body("Filier not found");
	}
	
	@PutMapping("updateFilier/{id}")
	public ResponseEntity<String> update(@PathVariable Long id ,@RequestBody filierDto e ){
		String res = filierserv.updatefiliere(id, e);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Filier updated with success");
		}
		return ResponseEntity.badRequest().body("Filier not found");
	}
	
	
	//----------- countmodule T_T ----------
	@GetMapping("nbrfilier")
	public ResponseEntity<Long> getNbFilier() {
		 Long count = filierrepo.nbFilier();
		 return ResponseEntity.ok(count);
	}
	
	@GetMapping("filierperetudiant")
	public ResponseEntity<List<EtudiantPerFilierChart>> etudiantPerFilier() {
		return ResponseEntity.ok(filierrepo.etudiantPerFilier());
	}
	
	
	
	
}
