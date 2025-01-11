package com.example.gestion_back.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotetoafficheDto {
	
	
	private Long id ;
	
	private String cinetu ;
	private String nameetu ;
	private String prenometu ;
	
	private String nomel ;
    
	
	private double notee;
    private String status;

    private String presence;

    private String typeev ;

}
