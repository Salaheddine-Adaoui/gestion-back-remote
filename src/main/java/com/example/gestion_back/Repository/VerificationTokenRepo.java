package com.example.gestion_back.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_back.Entities.VerificationToken;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken,Long> {

	VerificationToken findByToken(String token);
}
