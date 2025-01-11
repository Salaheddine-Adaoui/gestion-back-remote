package com.example.gestion_back.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.example.gestion_back.Dto.AjouteNoteDto;
import com.example.gestion_back.Dto.ModuleFilierDto;
import com.example.gestion_back.Dto.NotetoafficheDto;
import com.example.gestion_back.Dto.moduleDto;
import com.example.gestion_back.Entities.Moduleee;
import com.example.gestion_back.Entities.Note;
import com.example.gestion_back.Repository.filierRepo;
import com.example.gestion_back.Repository.noteRepo;
import com.example.gestion_back.Services.noteServices;

@RestController
@RequestMapping("note")
public class noteController {
	
	@Autowired
	noteServices noteserv;
	
	@Autowired
	noteRepo noterepo ;
	
	@PostMapping("addnote/{idel}/{cin}")
	public ResponseEntity<String> ajoutNote(@PathVariable Long idel,@PathVariable String cin,@RequestBody AjouteNoteDto ajoutdto){
		String res=noteserv.AjouterNote(idel, cin, ajoutdto);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Note added whit acces");
		}
		return ResponseEntity.badRequest().body("This element has not evaluation at the moment");
	}
	

	@GetMapping("getallnote/{code}")
	public ResponseEntity<List<NotetoafficheDto>>getNotetoaffiche(@PathVariable("code") String code) {
		
		return ResponseEntity.ok(noterepo.getNotetoaffiche(code));
		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deletebyid(@PathVariable Long id){
		String mes=noteserv.deleteNote(id);
		if(mes.equals("succes")) {
			return ResponseEntity.ok("Note deleted with succes");
		}
		return ResponseEntity.badRequest().body("Not not found");
	}
	
	@PutMapping("updatenote/{id}")
	public ResponseEntity<String> updatenote(@PathVariable("id") Long id,@RequestBody NotetoafficheDto ntad ){
		String res= noteserv.updateNote(id, ntad);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Note updated with succes");
		}
		return ResponseEntity.badRequest().body("Note have a proble with update");
		
	}
	
	@PostMapping("validatenote/{id}")
	public ResponseEntity<String> validateNote(@PathVariable Long id){
		String res=noteserv.validerNote(id);
		if(res.equals("succes")) {
			return ResponseEntity.ok("Note validated whit acces");
		}
		return ResponseEntity.badRequest().body("This note not found");
	}
	
	
	@GetMapping("/{id}")
    public ResponseEntity<NotetoafficheDto> getNoteById(@PathVariable Long id) {
        NotetoafficheDto noteDto = noteserv.getNoteById(id);
        if (noteDto != null) {
            return ResponseEntity.ok(noteDto);
        }
        return ResponseEntity.status(404).body(null); 
    }

	

}
