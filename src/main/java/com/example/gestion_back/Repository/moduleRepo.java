package com.example.gestion_back.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestion_back.Entities.Moduleee;

public interface moduleRepo extends JpaRepository<Moduleee,String> {
	Optional<Moduleee> findByCode(String code);

}
