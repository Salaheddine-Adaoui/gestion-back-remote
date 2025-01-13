package com.example.gestion_back.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gestion_back.Dto.EtudiantperYearChart;
import com.example.gestion_back.Dto.NotetoafficheDto;
import com.example.gestion_back.Dto.elementDto;
import com.example.gestion_back.Dto.etudiantDto;
import com.example.gestion_back.Entities.Etudiant;



public interface etudiantRepo extends JpaRepository<Etudiant,String> {
	Optional<Etudiant> findByCin(String cin);
	
	@Query("select count(e) from Etudiant e")
	Long nbEtudiant();
	
	@Query("select new com.example.gestion_back.Dto.etudiantDto(e.cin , e.nom , e.prenom , e.email , e.telephone , f.id, f.nom) from Etudiant e join e.etudiantElements eel join eel.element el join el.module m join m.filier f join el.prof p where p.code=:c ")
	List<etudiantDto> getetudiantbyprof(@Param("c") String code);
	
	

	

}
