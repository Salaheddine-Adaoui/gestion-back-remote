package com.example.gestion_back.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_back.Dto.evaluationDto;
import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Services.evaluationService;

@RestController
@RequestMapping("evaluation")
public class evaluationController {
	
	@Autowired
	evaluationService evalserv;
	
	@PostMapping("addEval")
	public ResponseEntity<String> addEval(@RequestBody evaluationDto evaldto){
		String res=evalserv.addEvaluation(evaldto);
		if(res.equals("succes")) {
			return ResponseEntity.ok("evaluation added whit succes");
		}
		return  ResponseEntity.badRequest().body("element not found");
	}
	
	@GetMapping("elementhasnoteval")
	public ResponseEntity<Object> elemnthasNoteEvalles(){
		List<Map<String,Object>> res=evalserv.elemnthasNoteEval();
		if(!res.isEmpty()) {
			return ResponseEntity.ok(res);
		}
		return  ResponseEntity.badRequest().body("all element are alredy evaluation , updater");
	}
	
	@GetMapping("allEval")
	public ResponseEntity<Object> allEvals(){
		List<Map<String,Object>> res=evalserv.allEvaluations();
		if(!res.isEmpty()) {
			return ResponseEntity.ok(res);
		}
		return  ResponseEntity.badRequest().body(null);
	}
	
	@GetMapping("allEvalDeelement/{id}")
	public ResponseEntity<Object> allEvalsdeElement(@PathVariable Long id){
		List<Map<String,Object>> res=evalserv.evaluationDEelement(id);
		if(!res.isEmpty()) {
			return ResponseEntity.ok(res);
		}
		return  ResponseEntity.badRequest().body("non evaluation pour ce element");
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteeval(@PathVariable Long id){
		String res=evalserv.deleteEvaluation(id);
		if(res.equals("succes")) {
			return ResponseEntity.ok("evaluation dleted whit succes");
		}
		return ResponseEntity.badRequest().body("evaluation not foud whit this id");
	}
}
