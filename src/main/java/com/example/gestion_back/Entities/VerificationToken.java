package com.example.gestion_back.Entities;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private Date expiryDate;

    @OneToOne(targetEntity =Professeur.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "prof_code")
    private Professeur prof;
}
