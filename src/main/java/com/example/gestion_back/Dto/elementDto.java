package com.example.gestion_back.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class elementDto {
	
	private Long id;
	private String nom;
	private double coiff;
	private String modulecode;
	private String profcode;
}