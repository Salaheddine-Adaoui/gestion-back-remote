package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    private String status;

    @Temporal(TemporalType.DATE)
    private Date dateValidation;

    @ManyToOne
    @JoinColumn(name="module_id")
    private Module module;

    @ManyToMany(mappedBy="element")
    private List<Professeur> professeur;

}
