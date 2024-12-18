package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Etudiant {
    @Id
    private String cin; // Assuming it's a unique identifier
    
    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name="filier_id")
    private Filier filier;

    @OneToMany(mappedBy = "etudiant")
    private List<Note> notes;

    // Getters et Setters
}
