package com.example.gestion_back.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Dto.EtudiantPerFilierChart;
import com.example.gestion_back.Dto.elementDto;
import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Entities.Etudiant;
import com.example.gestion_back.Entities.EtudiantElement;

@Repository
public interface etudiantelementRepo extends JpaRepository<EtudiantElement,Long> {

	
	List<EtudiantElement> findByEtudiantAndElement(Etudiant e,Element el);
	
	@Query("select count(e) from EtudiantElement e where e.element.id=:id")
	Long CountAll(@Param("id") Long id);
	
	@Query("select count(e) from EtudiantElement e where e.element.id=:id and e.status='valid'")
	Long isAllValid(@Param("id") Long id);
	
	@Query("select new  com.example.gestion_back.Dto.elementDto(e.id,e.nom,et.status) from Professeur p join p.element e join e.etudiantElements et where "+
	"p.code=:code ")
	List<elementDto> elementvalid(@Param("code") String code);
	
	
	
	

	
}
