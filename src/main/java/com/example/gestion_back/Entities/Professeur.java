package com.example.gestion_back.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professeur {
    @Id
    private String code; // Assuming it's a unique code, not auto-generated
    
    private String nom;
    private String prenom;
    private String specialite;


    @OneToOne
    @JoinColumn(name="compte_id")
    @JsonBackReference
    private Compte compte;
    
    @ManyToMany
    @JoinTable(
        name = "Enseigne",
        joinColumns = @JoinColumn(name = "prof_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "element_id"))
    @JsonBackReference
    private List<Element> element;

    // Getters et Setters
}

