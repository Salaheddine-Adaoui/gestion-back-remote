package com.example.gestion_back.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Entities.Professeur;

@Repository
public interface profRepo extends JpaRepository<Professeur,String> {
          Optional<Professeur> findByCode(String code);
          
         
          @Query("select count(p) > 0 from Professeur p where p.code=:c")
          boolean CodeDejaExist(@Param("c") String code) ;
        	  
          
          

         
}
