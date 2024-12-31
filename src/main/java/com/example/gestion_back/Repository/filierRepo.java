package com.example.gestion_back.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Dto.etudiantDto;
import com.example.gestion_back.Entities.Filier;

@Repository
public interface filierRepo extends JpaRepository<Filier,Long> {
	
	@Query("select count(f) from Filier f")
	Long nbFilier();
	
	
	
	//--------- Rida T-T -----------//
	@Query("select new com.example.gestion_back.Dto.etudiantDto (e.cin , e.nom , e.prenom , e.email , e.telephone , f.id , f.nom) from Etudiant e join e.filier f where f.id = :code")
	List<etudiantDto> getFilierEtudiants(@Param("code") Long code);
	//------------------------------//
	
	
}


