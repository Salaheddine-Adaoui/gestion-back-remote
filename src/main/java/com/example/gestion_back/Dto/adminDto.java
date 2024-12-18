package com.example.gestion_back.Dto;


import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class adminDto {
	 
	 private String nom;
	 private String prenom;
	 private String email;
	 private String password;
	 MultipartFile file;
}
