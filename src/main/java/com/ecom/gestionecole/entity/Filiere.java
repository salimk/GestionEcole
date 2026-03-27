package com.ecom.gestionecole.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @OneToMany(mappedBy = "filiere")
    private List<Etudiant> etudiants;


}
