package com.example.gestion_back.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Entities.Admin;

@Repository
public interface adminRepo extends JpaRepository<Admin,Long> {

}
