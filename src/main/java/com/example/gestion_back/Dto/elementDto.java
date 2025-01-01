package com.example.gestion_back.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class elementDto {
	
	public elementDto(Long idd ,String nomm) {
		id=idd;nom=nomm;
	}
	
	public elementDto(Long i,String n,String s) {
		id=i;nom=n;modulecode=s;
	}
	
	private Long id;
	private String nom;
	private double coiff;
	private String modulecode;
	private String profcode;
}

