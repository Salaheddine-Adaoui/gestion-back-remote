package com.example.gestion_back.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class profDto {
	
    private String code; // Assuming it's a unique code, not auto-generated
    private String nom;
    private String prenom;
    private String specialite;
    private String email;
    private String password;
    MultipartFile file;
    
	
}
