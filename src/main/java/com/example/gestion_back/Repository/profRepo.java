package com.example.gestion_back.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Entities.Professeur;

@Repository
public interface profRepo extends JpaRepository<Professeur,String> {
          Optional<Professeur> findByCode(String code);
         
          
}
