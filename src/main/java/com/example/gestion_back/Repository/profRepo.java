package com.example.gestion_back.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Dto.ElementsProfDto;
import com.example.gestion_back.Dto.PiechartProfDto;
import com.example.gestion_back.Dto.elementDto;
import com.example.gestion_back.Dto.listNoteProfDto;
import com.example.gestion_back.Entities.Professeur;

@Repository
public interface profRepo extends JpaRepository<Professeur,String> {
          Optional<Professeur> findByCode(String code);
          
         
          @Query("select count(p) > 0 from Professeur p where p.code=:c")
          boolean CodeDejaExist(@Param("c") String code) ;
          
          @Query("select new com.example.gestion_back.Dto.ElementsProfDto(e.nom,m.nom,f.nom ) from Professeur p join p.element e join e.module m "+
                  "join m.filier f where p.code=:c ")
          List<ElementsProfDto> getElementProf(@Param("c") String code) ;
          
       
          @Query("select new com.example.gestion_back.Dto.PiechartProfDto(f.nom, count(e) * 100.0 / (select  count(e2) from Professeur p2 join p2.element e2 join e2.module m2 join m2.filier f2 where p2.code = :c)) from Professeur p join p.element e join e.module m join m.filier f where p.code = :c group by f.nom")
          List<PiechartProfDto > profpychart(@Param("c") String code) ;
          
          
          @Query("select new com.example.gestion_back.Dto.elementDto(e.id,e.nom) from Professeur p join p.element e join e.etudiantElements el where p.code=:c and el.etudiant.cin=:cin")
          public List<elementDto> getElementProfEtudiant(@Param("c") String code,@Param("cin") String cin);
        	  
          @Query("select count(p) from Professeur p")
      	  Long nbProf();
          
          @Query("select new com.example.gestion_back.Dto.listNoteProfDto(e.cin , e.nom , e.prenom , el.nom ,et.elemNote, p.nom , p.prenom)"+
          " from Professeur p join p.element el join etudiantElements et join et.etudiant e "+
        		  "where p.code=:c and el.id=:id")
          List<listNoteProfDto> getNoteElement(@Param("c") String code,@Param("id") Long id);
        	  
          
          
          
          

         
}
