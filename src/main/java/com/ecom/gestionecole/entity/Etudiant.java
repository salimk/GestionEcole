package com.ecom.gestionecole.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue(value = "ETUDIANT")
public class Etudiant extends Personne {
    private String CNE;
    private String degree;
    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
}
