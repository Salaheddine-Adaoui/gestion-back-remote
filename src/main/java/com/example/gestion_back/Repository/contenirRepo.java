package com.example.gestion_back.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Dto.ModuleFilierDto;
import com.example.gestion_back.Entities.Filier;
import com.example.gestion_back.Entities.Moduleee;

@Repository
public interface contenirRepo extends JpaRepository<Moduleee,Long> {
	

    @Query("SELECT new com.example.gestion_back.Dto.ModuleFilierDto(f.id,m.code, m.nom, f.niveau, f.nom) FROM Moduleee m LEFT JOIN m.filier f")
    List<ModuleFilierDto> findModuleFilier();
    
   

    
}
