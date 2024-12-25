package com.example.gestion_back.Dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class etudiantDto {
	

    private String cin; // Assuming it's a unique identifier
    private String nom;
    private String prenom;
	

}
