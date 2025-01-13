package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
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
    
    @Temporal(TemporalType.DATE)
    private Date dat_inscription=new Date();

    @ManyToOne
    @JoinColumn(name="filier_id")
    @JsonBackReference
    private Filier filier;

    @OneToMany(mappedBy = "etudiant",cascade=CascadeType.ALL)
    private List<Note> notes;
    
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<EtudiantElement> etudiantElements;
}
