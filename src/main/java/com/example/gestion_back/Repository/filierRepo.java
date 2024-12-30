package com.example.gestion_back.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Entities.Filier;

@Repository
public interface filierRepo extends JpaRepository<Filier,Long> {
	
	@Query("select count(f) from Filier f")
	Long nbFilier();
}
