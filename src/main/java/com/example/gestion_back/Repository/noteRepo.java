package com.example.gestion_back.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.Evaluation;
import com.example.gestion_back.Entities.Note;

public interface noteRepo extends JpaRepository<Note,Long>{

	
	boolean existsByEtudiantAndEvaluation(Etudiant etudiant, Evaluation evaluation);
	List<Note> findByEtudiantAndEvaluation(Etudiant etudiant, Evaluation evaluation);
}
