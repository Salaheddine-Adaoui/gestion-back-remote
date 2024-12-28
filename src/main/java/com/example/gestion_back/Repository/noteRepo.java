package com.example.gestion_back.Repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.gestion_back.Entities.Note;

public interface noteRepo extends JpaRepository<Note,Long>{

}
