package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Element {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private double coefficient;


    @ManyToOne
    @JoinColumn(name="module_id")
    @JsonBackReference
    private Modulee module;

    @ManyToMany(mappedBy="element")
    private List<Professeur> professeur;

}
