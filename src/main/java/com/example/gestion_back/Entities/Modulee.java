package com.example.gestion_back.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modulee {
	
    @Id
    private String code;
    private String nom;

    @OneToMany(mappedBy = "module")
    @JsonManagedReference
    private List<Element> elements;
    
    @ManyToMany
    @JoinTable(
        name = "contenir",
        joinColumns = @JoinColumn(name = "module_id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "filier_id"))
    private List<Filier> filier;

}
