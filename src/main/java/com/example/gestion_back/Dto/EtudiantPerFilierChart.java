package com.example.gestion_back.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EtudiantPerFilierChart {
	
	private String nomf;
	private String niveau;
	private Long nombre;

}
