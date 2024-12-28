package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    private List<Etudiant> etudiants;

    @ManyToMany(mappedBy = "filier" , cascade = CascadeType.ALL)
    @JsonBackReference("moduleFiliers")
    private List<Moduleee> modules;

}
