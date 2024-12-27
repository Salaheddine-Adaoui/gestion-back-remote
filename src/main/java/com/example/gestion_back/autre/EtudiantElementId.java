package com.example.gestion_back.autre;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EtudiantElementId implements Serializable {
    private String cin; // CIN de l'étudiant
    private Long idElement; // ID de l'élément

    // Constructeurs, getters, setters, equals et hashCode
    public EtudiantElementId() {}

    public EtudiantElementId(String cin, Long idElement) {
        this.cin = cin;
        this.idElement = idElement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtudiantElementId that = (EtudiantElementId) o;
        return Objects.equals(cin, that.cin) && Objects.equals(idElement, that.idElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cin, idElement);
    }
}

