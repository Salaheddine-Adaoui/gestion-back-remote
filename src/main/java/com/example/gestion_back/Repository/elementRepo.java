package com.example.gestion_back.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gestion_back.Entities.Element;


public interface elementRepo extends JpaRepository<Element,Long> {

	
}
