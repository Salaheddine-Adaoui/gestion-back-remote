package com.example.gestion_back.Dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModuleFilierDto {
	private Long id;
	private String moduleCode;
    private String moduleNom;
    private String filierNiveau;
    private String filierNom;

}
