package com.example.gestion_back.Repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestion_back.Entities.Element;
import com.example.gestion_back.Entities.Evaluation;

@Repository
public interface evaluationRepo extends JpaRepository<Evaluation,Long> {

       
}
