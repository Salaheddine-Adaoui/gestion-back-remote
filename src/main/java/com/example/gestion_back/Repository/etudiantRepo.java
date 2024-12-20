package com.example.gestion_back.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_back.Entities.Etudiant;



public interface etudiantRepo extends JpaRepository<Etudiant,String> {
	Optional<Etudiant> findByCin(String cin);

}
