package com.example.gestion_back.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Entities.Compte;

@Repository
public interface userRepo extends JpaRepository<Compte,Long> {
	
	public Optional<Compte> findByEmail(String email);
	 @Query("SELECT COUNT(c) > 0 FROM Compte c WHERE c.email = :email AND c.prof.code <> :code")
     boolean existsByEmailAndNotCode(@Param("email") String email, @Param("code") String code);
	
}
