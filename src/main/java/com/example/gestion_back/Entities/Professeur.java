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
    private String tel;
    private String adresse;
    private boolean enabled;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="compte_id")
    @JsonBackReference
    private Compte compte;
    
    @OneToOne(mappedBy="prof",cascade = CascadeType.ALL)
    private VerificationToken token;
    
    @OneToMany(mappedBy="prof",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Element> element;

    // Getters et Setters
}

