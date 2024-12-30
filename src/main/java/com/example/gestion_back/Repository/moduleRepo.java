package com.example.gestion_back.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.gestion_back.Entities.Moduleee;

public interface moduleRepo extends JpaRepository<Moduleee,String> {
	Optional<Moduleee> findByCode(String code);
	
	@Query("SELECT m FROM Moduleee m JOIN FETCH m.filier f WHERE m.code = :code AND f.id = :id")
	Moduleee findModuleWithFilier(@Param("code") String code, @Param("id") Long id);

	@Query("select count(m) from Moduleee m")
	Long nbModule();
}
