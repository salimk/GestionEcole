package com.ecom.gestionecole.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entité représentant une filière (programme d'études).
 *
 * Annotations JPA utilisées :
 * - @Entity : marque la classe comme une entité JPA
 * - @Id : désigne le champ comme clé primaire
 * - @GeneratedValue : configure la génération automatique
 * - @OneToMany : relation un-à-plusieurs
 * - @ManyToMany : relation plusieurs-à-plusieurs
 * - @Temporal : pour les dates (si utilisation de java.util.Date)
 */
@Entity
@Table(name = "filieres", indexes = {
    @Index(name = "idx_filiere_nom", columnList = "nom")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"etudiants", "matieres", "professeurs"})
@EqualsAndHashCode(of = {"id"}, exclude = {"etudiants", "matieres", "professeurs"})
public class Filiere {

    // @Id : désigne le champ comme clé primaire
    // @GeneratedValue : génère automatiquement la valeur (IDENTITY = auto-increment)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom unique de la filière (ex: Informatique, Génie Civil, etc.)
    @Column(nullable = false, unique = true, length = 100)
    private String nom;

    // Description de la filière
    @Column(length = 500, columnDefinition = "TEXT")
    private String description;

    // Code de la filière (ex: INFO2024, GC2024)
    @Column(unique = true, length = 20)
    private String code;

    // Niveau d'études (ex: LICENCE, MASTER, DOCTORAT)
    @Column(nullable = false, length = 50)
    private String niveau = "LICENCE";

    // Durée en années
    @Column(name = "duree_annees")
    private Integer dureeAnnees;

    // Date de création de la filière
    @Column(name = "date_creation", updatable = false)
    private LocalDate dateCreation;

    // Capacité maximale d'étudiants
    @Column(name = "capacite_max")
    private Integer capaciteMax;

    /**
     * @OneToMany : relation un-à-plusieurs
     * Une filière contient plusieurs étudiants
     *
     * mappedBy = "filiere" : indique que la relation est gérée par l'attribut 'filiere' dans Etudiant
     * fetch = FetchType.LAZY : charge les étudiants à la demande (optimisation)
     * cascade = CascadeType.ALL : propage les opérations (persist, merge, remove)
     */
    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL, orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Etudiant> etudiants;

    /**
     * @OneToMany : relation un-à-plusieurs
     * Une filière contient plusieurs matières
     */
    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL, orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Matiere> matieres;

    /**
     * @ManyToMany : relation plusieurs-à-plusieurs (bidirectionnelle)
     * Une filière a plusieurs professeurs
     * Un professeur enseigne dans plusieurs filières
     *
     * mappedBy = "filieres" : indique que la relation est gérée par l'attribut 'filieres' dans Professeur
     */
    @ManyToMany(mappedBy = "filieres", fetch = FetchType.LAZY)
    private List<Professeur> professeurs;

    /**
     * Retourne le nombre d'étudiants inscrits
     */
    public int getNombreEtudiants() {
        return etudiants != null ? etudiants.size() : 0;
    }
}
