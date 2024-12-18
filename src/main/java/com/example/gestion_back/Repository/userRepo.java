package com.example.gestion_back.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Entities.Compte;

@Repository
public interface userRepo extends JpaRepository<Compte,Long> {
	
	public Optional<Compte> findByEmail(String email);
	
}
