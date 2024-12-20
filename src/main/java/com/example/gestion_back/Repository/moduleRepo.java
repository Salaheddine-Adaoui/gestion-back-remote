package com.example.gestion_back.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_back.Entities.Modulee;

public interface moduleRepo extends JpaRepository<Modulee,String> {
	Optional<Modulee> findByCode(String code);

}
