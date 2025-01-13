package com.example.gestion_back.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModuleCountByFilierDto {
	private String filierNiveauNom; 
    private Long moduleCount;       
}
