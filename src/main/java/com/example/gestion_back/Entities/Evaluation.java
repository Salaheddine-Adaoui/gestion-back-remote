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
public class Evaluation {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String mode;
    private double coefficient;

    @OneToOne(mappedBy = "evaluation")
    private Note notes;

    @ManyToOne
    @JoinColumn(name="element_id")
    private Element element;

}
