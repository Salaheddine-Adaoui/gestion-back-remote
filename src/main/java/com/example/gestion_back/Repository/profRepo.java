package com.example.gestion_back.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Dto.ElementsProfDto;
import com.example.gestion_back.Dto.PiechartProfDto;
import com.example.gestion_back.Entities.Professeur;

@Repository
public interface profRepo extends JpaRepository<Professeur,String> {
          Optional<Professeur> findByCode(String code);
          
         
          @Query("select count(p) > 0 from Professeur p where p.code=:c")
          boolean CodeDejaExist(@Param("c") String code) ;
          
          @Query("select new com.example.gestion_back.Dto.ElementsProfDto(e.nom,m.nom,f.nom ) from Professeur p join p.element e join e.module m "+
                  "join m.filier f where p.code=:c "
        		  
        		  )
          List<ElementsProfDto> getElementProf(@Param("c") String code) ;
          
          //@Query("select new com.example.gestion_back.Dto.PiechartProfDto(f.nom,count(e)) from Professeur p join p.element e join e.module m "+
        		  //"join m.filier f where p.code=:c group by f.nom ")
          @Query("select new com.example.gestion_back.Dto.PiechartProfDto(f.nom, count(e) * 100.0 / (select  count(e2) from Professeur p2 join p2.element e2 join e2.module m2 join m2.filier f2 where p2.code = :c)) from Professeur p join p.element e join e.module m join m.filier f where p.code = :c group by f.nom")
          List<PiechartProfDto > profpychart(@Param("c") String code) ;
        	  
          
          
          
          

         
}
