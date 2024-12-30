package com.example.gestion_back.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjouteNoteDto {
	
	Long idcc;
	double notecc;
	String presenceCC;
	
	Long idtp;
	double notetp;
	String presenceTP;
	
	Long idpres;
	double notepres;
	String presencePRES;
	
	Long idproj;
	double noteproj;
	String presencePROJ;

}
