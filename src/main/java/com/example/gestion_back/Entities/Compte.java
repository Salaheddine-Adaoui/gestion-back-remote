package com.example.gestion_back.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "compte")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Compte {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String email;
	private String password;
	private String role;
	
	@Lob
	private String image;
	
	@OneToOne(mappedBy="compte")
	private Admin admin;
	
	@OneToOne(mappedBy="compte")
    @JsonManagedReference
	private Professeur prof;
	
}
