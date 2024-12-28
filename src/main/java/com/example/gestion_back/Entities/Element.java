package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private String status = "pending";
    



    @ManyToOne
    @JoinColumn(name="module_id")
    private Moduleee module;

    @ManyToOne
    @JoinColumn(name="prof_id")
    @JsonBackReference
    private Professeur prof ;
    
    @OneToMany(mappedBy = "element", cascade = CascadeType.ALL)
    private List<EtudiantElement> etudiantElements;

}
