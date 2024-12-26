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

import com.example.gestion_back.Dto.ModuleFilierDto;
import com.example.gestion_back.Dto.moduleDto;
import com.example.gestion_back.Dto.updateModFilDto;
import com.example.gestion_back.Entities.Moduleee;
import com.example.gestion_back.Services.moduleService;

@RestController
@RequestMapping("module")
public class moduleController {
	
	@Autowired
	moduleService moduleserv;
	
	@PostMapping("/add")
	public ResponseEntity<String> addModule(@RequestBody moduleDto m){
		
		String res=moduleserv.saveModule(m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("module added whit succes");
		}
		return ResponseEntity.badRequest().body("errure module not added");

	}
	
	@PostMapping("moduletofilier/{code}/{id}")
	public ResponseEntity<String> assigneModuleToFilier(
	        @PathVariable("code") String code,
	        @PathVariable("id") Long id) {
			
			String res=moduleserv.assignerModulToFilier(id, code);
			if(res.equals("succes")) {
				return ResponseEntity.ok("module assigned whit succes");
			}
			return ResponseEntity.badRequest().body("errure module ou filier not found");
	
		}
	
	@GetMapping("getmoduletfilier/{code}/{id}")
	public ResponseEntity<Map<String,Object>> getModuleToFilier(
			@PathVariable("code") String code,
			@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(moduleserv.getModuleFilier(id, code));
		
	}
	
	@DeleteMapping("delmoduletofilier/{code}/{id}")
	public ResponseEntity<String> deleteModuleToFilier(
			@PathVariable("code") String code,
			@PathVariable("id") Long id) {
		
		String res=moduleserv.dletefilierfrommodule(id, code);
		if(res.equals("succes")) {
			return ResponseEntity.ok("module exracted from filier whit succes");
		}
		return ResponseEntity.badRequest().body("errure module ou filier not found");
		
	}
	
	@GetMapping("/allmodulefilier")
	public ResponseEntity<List<ModuleFilierDto>> allModuleFilier(){
		return ResponseEntity.ok(moduleserv.findModuleFilier());
	}
	
	@GetMapping("all")
	public ResponseEntity<List<Moduleee>> findall(){
		return ResponseEntity.ok(moduleserv.allModule());
	}
	
	@GetMapping("Byid/{code}")
	public ResponseEntity<Object> findbuid(@PathVariable String  code){
		Moduleee m=moduleserv.findModule(code);
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
	
	@PutMapping("update/{code}/{id}")
	public ResponseEntity<String> update(@PathVariable("code") String code,@PathVariable("id") Long id,@RequestBody updateModFilDto m ){
		String res=moduleserv.updateModFil(id,code,m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("module updated with succes");
		}
		return ResponseEntity.badRequest().body("module or filier not found whit this infos");
		
	}
	@PutMapping("updatemod/{code}")
	public ResponseEntity<String> updatee(@PathVariable("code") String code,@RequestBody moduleDto m ){
		String res=moduleserv.updateModule(code,m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("module updated with succes");
		}
		return ResponseEntity.badRequest().body("module or filier not found whit this infos");
		
	}
	
}
