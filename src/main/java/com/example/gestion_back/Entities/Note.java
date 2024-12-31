package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    double notee;
    private String status="pending";

    private String presence;

    @ManyToOne
    @JoinColumn(name="etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name="evaluation_id")
    private Evaluation evaluation;

}
