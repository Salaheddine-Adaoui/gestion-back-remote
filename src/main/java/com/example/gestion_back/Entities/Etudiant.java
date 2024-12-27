package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Etudiant {
    @Id
    private String cin; 
    
    private String nom;
    private String prenom;

    private String email;
    private String telephone;

    @ManyToOne
    @JoinColumn(name="filier_id")
    @JsonBackReference
    private Filier filier;

    @OneToMany(mappedBy = "etudiant")
    private List<Note> notes;
    
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<EtudiantElement> etudiantElements;
}
