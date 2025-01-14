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

import com.example.gestion_back.Dto.ModuleCountByFilierDto;
import com.example.gestion_back.Dto.ModuleFilierDto;
import com.example.gestion_back.Dto.moduleDto;
import com.example.gestion_back.Dto.updateModFilDto;
import com.example.gestion_back.Entities.Moduleee;
import com.example.gestion_back.Repository.contenirRepo;
import com.example.gestion_back.Repository.moduleRepo;
import com.example.gestion_back.Services.moduleService;

@RestController
@RequestMapping("module")
public class moduleController {
	
	@Autowired
	moduleService moduleserv;
	
	@Autowired
	moduleRepo modulerepo;
	
	@Autowired
	contenirRepo contenirrepo;
	
	
	@PostMapping("/add")
	public ResponseEntity<String> addModule(@RequestBody moduleDto m){
		
		String res=moduleserv.saveModule(m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Module added with success");
		}
		return ResponseEntity.badRequest().body("Module not added");

	}
	
	@PostMapping("moduletofilier/{code}/{id}")
	public ResponseEntity<String> assigneModuleToFilier(
	        @PathVariable("code") String code,
	        @PathVariable("id") Long id) {
			
			String res=moduleserv.assignerModulToFilier(id, code);
			if(res.equals("succes")) {
				return ResponseEntity.ok("Module assigned whit succes");
			}
			return ResponseEntity.badRequest().body("Module or Filier not found");
	
		}
	
	@GetMapping("allmoduless")
	public ResponseEntity<List<moduleDto>> modulesss(){
			
			return ResponseEntity.ok(moduleserv.allModule());
	
		}
	
	@GetMapping("getmoduletfilier/{code}/{id}")
	public ResponseEntity<Map<String,Object>> getModuleToFilier(
			@PathVariable("code") String code,
			@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(moduleserv.getModuleFilier(id, code));
		
	}
	
	/*@DeleteMapping("delmoduletofilier/{code}/{id}")
	public ResponseEntity<String> deleteModuleToFilier(
			@PathVariable("code") String code,
			@PathVariable("id") Long id) {
		
		String res=moduleserv.dletefilierfrommodule(id, code);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Module exracted from Filier with success");
		}
		return ResponseEntity.badRequest().body("Module or Filier not found");
		
	}*/
	
	@GetMapping("/allmodulefilier")
	public ResponseEntity<List<ModuleFilierDto>> allModuleFilier(){
		return ResponseEntity.ok(moduleserv.findModuleFilier());
	}
	

	
	@GetMapping("Byid/{code}")
	public ResponseEntity<Object> findbuid(@PathVariable String  code){
		Map<String,String> m=moduleserv.findModule(code);
		if(m!=null) {
			return ResponseEntity.ok(m);
		}
		return ResponseEntity.badRequest().body("Module not found");
	}
	
	@DeleteMapping("delete/{code}")
	public ResponseEntity<String> deletebyid(@PathVariable String code){
		String mes=moduleserv.deleteModule(code);
		if(mes.equals("succes")) {
			return ResponseEntity.ok("Module deleted with succes");
		}
		return ResponseEntity.badRequest().body("Module not found");
	}
	
	/*@PutMapping("update/{code}/{id}")
	public ResponseEntity<String> update(@PathVariable("code") String code,@PathVariable("id") Long id,@RequestBody updateModFilDto m ){
		String res=moduleserv.updateModFil(id,code,m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Module updated with success");
		}
		return ResponseEntity.badRequest().body("Module or Filier not found");
		
	// update nom de module
	}*/
	@PutMapping("updatemod/{code}")
	public ResponseEntity<String> updatee(@PathVariable("code") String code,@RequestBody moduleDto m ){
		String res=moduleserv.updateModule(code,m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Module updated with succes");
		}
		return ResponseEntity.badRequest().body("Module or Filier not found");
		
	}
	
	//----------- countmodule T_T ----------
	@GetMapping("nbrmodule")
	public ResponseEntity<Long> getNbModule() {
	   Long count = modulerepo.nbModule();
	   return ResponseEntity.ok(count);
	}
	
	//============= Module Count by Filier =================
	@GetMapping("modulecountbyfilier")
	public Map<String, List<ModuleCountByFilierDto>> getModulesGroupedByFiliere() {
        return moduleserv.getModulesGroupedByFiliere();
    }
	
}
