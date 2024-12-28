package com.example.gestion_back.Controller;

import java.util.List;

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

import com.example.gestion_back.Dto.etudiantDto;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Services.etudiantService;

@RestController
@RequestMapping("etudiant")
public class etudiantController {
	
	@Autowired
	etudiantService etudiantserv ;
	
	
	@PostMapping("addEtudiant")
	public ResponseEntity<String> addEtudiant(@RequestBody etudiantDto e){
		
		String res = etudiantserv.saveEtudiant(e);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Etudiant added with success");
		}
		return ResponseEntity.badRequest().body("Etudiant not added");

	}
	
	@GetMapping("allEtudiants")
	public ResponseEntity<List<etudiantDto>> findall(){
		return ResponseEntity.ok(etudiantserv.getAllEtudiants());
	}
	
	@GetMapping("cin/{cin}")
	public ResponseEntity<Object> findbuid(@PathVariable String  cin){
		Etudiant e = etudiantserv.findEtudiant(cin);
		if(e!=null) {
			return ResponseEntity.ok(e);
		}
		return ResponseEntity.badRequest().body("Etudiant not found");
	}
	
	@DeleteMapping("deleteEtudiant/{cin}")
	public ResponseEntity<String> deletebyid(@PathVariable String cin){
		String mes= etudiantserv.deleteEtudiant(cin);
		if(mes.equals("succes")) {
			return ResponseEntity.ok("Etudiant deleted with success");
		}
		return ResponseEntity.badRequest().body("Etudiant not found");
	}
	
	@PutMapping("updateEtudiant/{cin}")
	public ResponseEntity<String> update(@PathVariable String cin,@RequestBody Etudiant e ){
		String res=etudiantserv.updateEtudiant(cin, e);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Etudiant updated with success");
		}
		return ResponseEntity.badRequest().body("Etudiant not found");
		
	}
	
	
	@PostMapping("filiertoetudiant/{cin}/{id}")
	public ResponseEntity<String> assigneetfil(@PathVariable String cin,@PathVariable Long id){
		String res=etudiantserv.assignerFilToEtudiant(cin, id);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Filier assigned with success");
		}
		return ResponseEntity.badRequest().body("Etudiant or Filier not found");
	}

}
 