package com.example.gestion_back.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class evaluationDto {
	
	private Long elid;
	private boolean cc;
	private double cccoif;
	private boolean tp;
	private double tpcoif;
	private boolean proj;
	private double projcoif;
	private boolean pres;
	private double prescoif;

}
