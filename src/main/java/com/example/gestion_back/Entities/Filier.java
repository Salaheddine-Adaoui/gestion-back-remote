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
public class Filier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nom;
    private String departement;
    private String niveau;

    @OneToMany(mappedBy = "filier")
    private List<Etudiant> etudiants;

    @ManyToMany(mappedBy = "filier")
    private List<Modulee> modules;

}
