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
import com.example.gestion_back.Entities.Modulee;
import com.example.gestion_back.Services.moduleService;

@RestController
@RequestMapping("module")
public class moduleController {
	
	@Autowired
	moduleService moduleserv;
	
	@PostMapping("add")
	public ResponseEntity<String> addModule(@RequestBody Modulee m){
		
		String res=moduleserv.saveModule(m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("module added whit succes");
		}
		return ResponseEntity.badRequest().body("errure module not added");

	}
	
	@GetMapping("all")
	public ResponseEntity<List<Modulee>> findall(){
		return ResponseEntity.ok(moduleserv.allModule());
	}
	
	@GetMapping("Byid/{code}")
	public ResponseEntity<Object> findbuid(@PathVariable String  code){
		Modulee m=moduleserv.findModule(code);
		if(m!=null) {
			return ResponseEntity.ok(m);
		}
		return ResponseEntity.badRequest().body("erreure not found");
	}
	
	@DeleteMapping("delete/{code}")
	public ResponseEntity<String> deletebyid(@PathVariable String code){
		String mes=moduleserv.deleteModule(code);
		if(mes.equals("succes")) {
			return ResponseEntity.ok("module deleted with succes");
		}
		return ResponseEntity.badRequest().body("module not found");
	}
	
	@PutMapping("update/{code}")
	public ResponseEntity<String> update(@PathVariable String code,@RequestBody Modulee m ){
		String res=moduleserv.updateModule(code,m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("module deleted with succes");
		}
		return ResponseEntity.badRequest().body("module not found whit this code");
		
	}
	
}
