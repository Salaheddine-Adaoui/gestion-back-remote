package com.example.gestion_back.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestion_back.Dto.AjouteNoteDto;
import com.example.gestion_back.Dto.NotetoafficheDto;
import com.example.gestion_back.Dto.moduleDto;
import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.EtudiantElement;
import com.example.gestion_back.Entities.Evaluation;
import com.example.gestion_back.Entities.Moduleee;
import com.example.gestion_back.Entities.Note;
import com.example.gestion_back.Repository.elementRepo;
import com.example.gestion_back.Repository.etudiantRepo;
import com.example.gestion_back.Repository.etudiantelementRepo;
import com.example.gestion_back.Repository.evaluationRepo;
import com.example.gestion_back.Repository.noteRepo;
import com.example.gestion_back.Repository.profRepo;

@Service
public class noteServices {
	
	@Autowired
	etudiantRepo etudrepo;
	
	@Autowired
	elementRepo elementrepo;
	
	@Autowired
	profRepo profrepo;
	
	@Autowired
	noteRepo noterepo;
	
	@Autowired
	evaluationRepo evalrepo;
	
	@Autowired
	etudiantelementRepo etelrepo;
	
	
	public String AjouterNote(Long elid,String cin,AjouteNoteDto ajoutdto) {
		
		Optional<Etudiant> et=etudrepo.findByCin(cin);
		Optional<Element> el=elementrepo.findById(elid);
		
		
		if(et.isPresent() && el.isPresent()) {
			double m=0;
			Etudiant ett=et.get();
			Element ell=el.get();
			if(ajoutdto.getIdcc()!=null) {
				Optional<Evaluation> e=evalrepo.findById(ajoutdto.getIdcc());
				if(e.isPresent()) {
					Evaluation ee=e.get();
					Note note;
					boolean isExist=noterepo.existsByEtudiantAndEvaluation(ett, ee);
					if(isExist) {
						note=noterepo.findByEtudiantAndEvaluation(ett, ee).get(0);
					}
					else {
						note =new Note();
					}
					
					note.setEtudiant(ett);
					double not=ajoutdto.getNotecc()*ee.getCoiff();
					m=m+not;
					note.setNotee(ajoutdto.getNotecc());
					note.setEvaluation(ee);
					if(ajoutdto.getPresenceCC().equals("P")) {
						note.setPresence("present");
					}else {
						note.setNotee(0);
						note.setPresence("absent");
					}
					noterepo.save(note);
				}
				
			}
			if(ajoutdto.getIdtp()!=null) {
				Optional<Evaluation> e=evalrepo.findById(ajoutdto.getIdtp());
				if(e.isPresent()) {
					Evaluation ee=e.get();
					Note note;
					boolean isExist=noterepo.existsByEtudiantAndEvaluation(ett, ee);
					if(isExist) {
						note=noterepo.findByEtudiantAndEvaluation(ett, ee).get(0);
					}
					else {
						note =new Note();
					}
					note.setEtudiant(ett);
					double not=ajoutdto.getNotetp()*ee.getCoiff();
					m=m+not;
					note.setNotee(ajoutdto.getNotetp());
					note.setEvaluation(ee);
					if(ajoutdto.getPresenceTP().equals("P")) {
						note.setPresence("present");
					}else {
						note.setNotee(0);
						note.setPresence("absent");
					}
					noterepo.save(note);
				}
				
			}
			if(ajoutdto.getIdpres()!=null) {
				Optional<Evaluation> e=evalrepo.findById(ajoutdto.getIdpres());
				if(e.isPresent()) {
					Evaluation ee=e.get();
					Note note;
					boolean isExist=noterepo.existsByEtudiantAndEvaluation(ett, ee);
					if(isExist) {
						note=noterepo.findByEtudiantAndEvaluation(ett, ee).get(0);
					}
					else {
						note =new Note();
					}
					note.setEtudiant(ett);
					double not=ajoutdto.getNotepres()*ee.getCoiff();
					m=m+not;
					note.setNotee(ajoutdto.getNotepres());
					note.setEvaluation(ee);
					if(ajoutdto.getPresencePRES().equals("P")) {
						note.setPresence("present");
					}else {
						note.setNotee(0);
						note.setPresence("absent");
					}
					noterepo.save(note);
				}
				
			}
			if(ajoutdto.getIdproj()!=null) {
				Optional<Evaluation> e=evalrepo.findById(ajoutdto.getIdproj());
				if(e.isPresent()) {
					Evaluation ee=e.get();
					Note note;
					boolean isExist=noterepo.existsByEtudiantAndEvaluation(ett, ee);
					if(isExist) {
						note=noterepo.findByEtudiantAndEvaluation(ett, ee).get(0);
					}
					else {
						note =new Note();
					}
					note.setEtudiant(ett);
					double not=ajoutdto.getNoteproj()*ee.getCoiff();
					m=m+not;
					note.setNotee(ajoutdto.getNoteproj());
					note.setEvaluation(ee);
					if(ajoutdto.getPresencePROJ().equals("P")) {
						note.setPresence("present");
					}else {
						note.setNotee(0);
						note.setPresence("absent");
					}
					noterepo.save(note);
				}
				
				List<EtudiantElement> eeee=etelrepo.findByEtudiantAndElement(ett, ell);
				for(EtudiantElement i:eeee) {
					i.setElemNote(m);
					i.setStatus("pending");
					etelrepo.save(i);
				}
				
				return "succes";
			}
			
		}
		return "failed";
	
	}
	

	public String deleteNote(Long id) {
		
		Optional<Note> n=noterepo.findById(id);
		
		if(n.isPresent()) {
			Note no = n.get();
			noterepo.delete(no);

			return "succes";
		}
		return "failed";
	}
	
	public String updateNote(Long id , NotetoafficheDto ntad) {
		Optional<Note> n = noterepo.findById(id);
		
		if (n.isPresent()) {
			Note no =  n.get();
			no.setNotee(ntad.getNotee());
			no.setPresence(ntad.getPresence());
			no.setStatus(ntad.getStatus());
			
			noterepo.save(no);
			return "succes";			
		}
		return "failed";
		
	}
	
	



	
	public String validerNote(Long idnote) {
		
		Note note=noterepo.findById(idnote).orElseThrow(
				()->new RuntimeException("note not found"));
		note.setStatus("valid");
		noterepo.save(note);
	    Etudiant et=note.getEtudiant();
	    Element el=note.getEvaluation().getElement();
	    List<Note> notes_etudiant=noterepo.findByEtudiant(et);
	    int count=0;
	    for(Note not:notes_etudiant) {
	    	if (!not.getStatus().equals("valid")) {
	    		count=count+1;
	    		
	    	}
	    }
	    if(count==0) {
	    	List<EtudiantElement> elet=etelrepo.findByEtudiantAndElement(et, el);
	    	for(EtudiantElement elett:elet) {
	    		elett.setStatus("valid");
	    		etelrepo.save(elett);
	    	}
	    }
		return "succes";
	}
	
	
	
	
	
	public NotetoafficheDto getNoteById(Long id) {
        Optional<Note> noteOptional = noterepo.findById(id);

        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();
            
            return new NotetoafficheDto(
                note.getId(),
                note.getEtudiant().getCin(),   
                note.getEtudiant().getNom(),   
                note.getEtudiant().getPrenom(),
                note.getEvaluation().getElement().getNom(),  
                note.getNotee(),
                note.getStatus(),
                note.getPresence(),
                note.getEvaluation().getType()  
            );
        }
        return null; 
    }
	

}
