package com.ecom.gestionecole.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@DiscriminatorValue(value = "PROFESSEUR")
public class Professeur extends Personne {

    private String specialite;
    private String grade;

    @ManyToMany
    @JoinTable(name = "filiereprof")
    private List<Filiere> filieres;

}
