package com.example.gestion_back.Entities;


import jakarta.persistence.*;

@Entity
public class EtudiantElement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idd;
	 private String status; 
	 private Double elemNote;
	
	

    @ManyToOne
    @JoinColumn(name = "cin")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "idElement")
    private Element element;

   
    


}