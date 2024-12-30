package com.example.gestion_back.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
