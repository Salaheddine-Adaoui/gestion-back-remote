package com.example.gestion_back.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_back.Dto.AjouteNoteDto;
import com.example.gestion_back.Services.noteServices;

@RestController
@RequestMapping("note")
public class noteController {
	
	@Autowired
	noteServices noteserv;
	
	@PostMapping("addnote/{idel}/{cin}")
	public ResponseEntity<String> ajoutNote(@PathVariable Long idel,@PathVariable String cin,@RequestBody AjouteNoteDto ajoutdto){
		String res=noteserv.AjouterNote(idel, cin, ajoutdto);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Note added whit acces");
		}
		return ResponseEntity.badRequest().body("This element has not evaluation at the moment");
	}
}
