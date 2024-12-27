package com.example.gestion_back.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class elementDto {
	
	private String nom;
    private double coefficient;
    private String moduleId ;
    
}
