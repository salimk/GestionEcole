package com.ecom.gestionecole.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entité représentant un professeur.
 * Hérite de Personne avec une stratégie SINGLE_TABLE.
 *
 * Annotations JPA utilisées :
 * - @DiscriminatorValue : spécifie la valeur du discriminant
 * - @ManyToMany : relation plusieurs-à-plusieurs
 * - @JoinTable : configure la table de jonction
 * - @OneToMany : relation un-à-plusieurs (un professeur évalue plusieurs étudiants)
 */
@Entity
@DiscriminatorValue(value = "PROFESSEUR")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Professeur extends Personne {

    // Spécialité du professeur (ex: Mathématiques, Physique, etc.)
    @Column(nullable = false, length = 100)
    private String specialite;

    // Grade académique (ex: MAITRE, DOCTEUR, PROFESSEUR, etc.)
    @Column(nullable = false, length = 50)
    private String grade;

    // Numéro de matricule unique
    @Column(unique = true, nullable = false, length = 20)
    private String matricule;

    // Date d'embauche
    @Column(name = "date_embauche")
    private LocalDate dateEmbauche;

    // Numéro de téléphone professionnel
    @Column(length = 15, name = "telephone_professionnel")
    private String telephoneProfessionnel;

    // Bureau/Localisation
    @Column(length = 50)
    private String bureau;

    /**
     * @ManyToMany : relation plusieurs-à-plusieurs
     * Un professeur peut enseigner dans plusieurs filières
     * Plusieurs filières peuvent avoir plusieurs professeurs
     *
     * @JoinTable : crée une table de jonction
     * name = "professeur_filiere" : nom de la table de jonction
     * joinColumns : clé étrangère du côté Professeur
     * inverseJoinColumns : clé étrangère du côté Filiere
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "professeur_filiere",
        joinColumns = @JoinColumn(name = "professeur_id"),
        inverseJoinColumns = @JoinColumn(name = "filiere_id")
    )
    private List<Filiere> filieres;

    /**
     * @ManyToMany : relation plusieurs-à-plusieurs
     * Un professeur enseigne plusieurs matières
     * Une matière peut être enseignée par plusieurs professeurs
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "professeur_matiere",
        joinColumns = @JoinColumn(name = "professeur_id"),
        inverseJoinColumns = @JoinColumn(name = "matiere_id")
    )
    private List<Matiere> matieres;

    /**
     * @OneToMany : relation un-à-plusieurs
     * Un professeur crée plusieurs évaluations
     * cascade = CascadeType.PERSIST : persiste les évaluations quand le professeur est créé
     * cascade = CascadeType.MERGE : fusionne les évaluations quand le professeur est fusionné
     * orphanRemoval = true : supprime les évaluations orphelines
     */
    @OneToMany(mappedBy = "professeur", cascade = {CascadeType.PERSIST, CascadeType.MERGE},
               orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;
}
