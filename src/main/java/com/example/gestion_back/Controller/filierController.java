package com.example.gestion_back.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_back.Dto.filierDto;
import com.example.gestion_back.Dto.moduleDto;
import com.example.gestion_back.Services.filierService;
import com.example.gestion_back.Services.moduleService;


@RestController
@RequestMapping("filier")
public class filierController {
   
	@Autowired
	filierService filierserv;
	
	@PostMapping("/add")
	public ResponseEntity<String> addFilier(@RequestBody filierDto m){
		
		String res=filierserv.saveFilier(m);
		if(res.equals("succes")) {
			return ResponseEntity.ok("filier added whit succes");
		}
		return ResponseEntity.badRequest().body("errure module not added");

	}
	
}
