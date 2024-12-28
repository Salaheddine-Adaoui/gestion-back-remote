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

    private String status;
    private String presence;

    @ManyToOne
    @JoinColumn(name="etudiant_id")
    private Etudiant etudiant;

    @OneToOne
    @JoinColumn(name="evaluation_id")
    private Evaluation evaluation;
    

}
