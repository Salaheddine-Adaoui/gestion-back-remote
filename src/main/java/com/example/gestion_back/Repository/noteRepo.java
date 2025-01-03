package com.example.gestion_back.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gestion_back.Dto.NotetoafficheDto;
import com.example.gestion_back.Dto.listNoteProfDto;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.Evaluation;
import com.example.gestion_back.Entities.Note;

public interface noteRepo extends JpaRepository<Note,Long>{

	
	boolean existsByEtudiantAndEvaluation(Etudiant etudiant, Evaluation evaluation);
	List<Note> findByEtudiantAndEvaluation(Etudiant etudiant, Evaluation evaluation);
	
	
	@Query("select new com.example.gestion_back.Dto.NotetoafficheDto(n.id , e.nom , e.prenom , n.notee , n.status ,n.presence , ev.type) from Etudiant e join e.notes n join n.evaluation ev join ev.element el join el.prof p where p.code=:c ")
	List<NotetoafficheDto> getNotetoaffiche(@Param("c") String code);

}
