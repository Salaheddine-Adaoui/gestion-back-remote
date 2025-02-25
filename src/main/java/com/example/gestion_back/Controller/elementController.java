package com.example.gestion_back.Controller;

import java.util.HashMap;
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
import com.example.gestion_back.Dto.elementDto;
import com.example.gestion_back.Dto.listNoteProfDto;
import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Repository.etudiantelementRepo;
import com.example.gestion_back.Repository.profRepo;
import com.example.gestion_back.Services.elementService;


@RestController
@RequestMapping("element")
public class elementController {
	
	
	@Autowired
	elementService elemserv ;
	
	@Autowired
	profRepo profrepo ;
	
	@Autowired
	etudiantelementRepo etelrepo ;
	
	
	@PostMapping("addElement")
	public ResponseEntity<String> addElemnt(@RequestBody elementDto e){
		
		String res = elemserv.saveElement(e);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Element added with success");
		}
		return ResponseEntity.badRequest().body("Element not added");

	}
	
	@PostMapping("assignerProf/{code}/{id}")
	public ResponseEntity<String> assignerProf(@PathVariable String code,@PathVariable Long id){
		
		String res = elemserv.asignerProf(code, id);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Prof assigned to Element with success");
		}
		return ResponseEntity.badRequest().body("Prof not assigned");
		
	}
	
	@GetMapping("allElements")
	public ResponseEntity<List<Map<String,Object>>> findall(){
		return ResponseEntity.ok(elemserv.allElements());
	}
	
	@GetMapping("byid/{id}")
	public ResponseEntity<Map<String,Object>> findbuid(@PathVariable Long id){
		Map<String,Object> e = elemserv.findElement(id);
		if(e!=null) {
			return ResponseEntity.ok(e);
		}
		Map<String,Object> err=new HashMap<>();
		err.put("error", "Element not found");
		return ResponseEntity.badRequest().body(err);
	}
	
	@DeleteMapping("deleteElement/{id}")
	public ResponseEntity<String> deletebyid(@PathVariable Long id){
		String mes= elemserv.deleteElement(id);
		if(mes.equals("succes")) {
			return ResponseEntity.ok("Element deleted with succes");
		}
		return ResponseEntity.badRequest().body("Element not found");
	}
	
	@PutMapping("updateElement/{id}")
	public ResponseEntity<String> update(@PathVariable Long id,@RequestBody elementDto e ){
		String res=elemserv.updateElement(id, e);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Element updated with success");
		}
		return ResponseEntity.badRequest().body("Element not found");
		
	}
	
	@GetMapping("elemproffetudiant/{code}/{cin}")
	public ResponseEntity<List<elementDto>> getElementPofEtud(@PathVariable String code,@PathVariable String cin ){
		 return ResponseEntity.ok(profrepo.getElementProfEtudiant(code, cin));
	}
	
	@GetMapping("Allcount/{id}")
	public ResponseEntity<Long> getAllcount(@PathVariable Long id){
		return ResponseEntity.ok(etelrepo.CountAll(id));
	}
	@GetMapping("Allcountvalid/{id}")
	public ResponseEntity<Long> getAllcountvalid(@PathVariable Long id){
		return ResponseEntity.ok(etelrepo.isAllValid(id));
	}
	
	@GetMapping("Allelementvalid/{code}")
	public ResponseEntity<Object> getAllelemntvalid(@PathVariable String code){
		List<Map<String,Object>> list=elemserv.getAllValidByID(code);
		if(!list.isEmpty()) {
			return ResponseEntity.ok(list);
		}
		return ResponseEntity.badRequest().body("no element valid yet");
	}
	
	@GetMapping("getnoteElment/{code}/{id}")
	public ResponseEntity< List<listNoteProfDto>> getnoteElement(@PathVariable String code,@PathVariable Long id){
		
		 List<listNoteProfDto> list = profrepo.getNoteElement(code, id);
			return ResponseEntity.ok(list);
		
	}
	
}
