package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moduleee {
	
    @Id
    private String code;
    private String nom;

    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Element> elements;
    
    @ManyToOne
    private Filier filier;

}
