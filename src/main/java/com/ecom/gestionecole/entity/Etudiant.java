package com.ecom.gestionecole.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Entité représentant un étudiant.
 * Hérite de Personne avec une stratégie SINGLE_TABLE.
 *
 * Annotations JPA utilisées :
 * - @DiscriminatorValue : spécifie la valeur du discriminant pour cette classe
 * - @ManyToOne : relation plusieurs-à-un (plusieurs étudiants pour une filière)
 * - @JoinColumn : configure la clé étrangère
 * - @OneToMany : relation un-à-plusieurs (un étudiant a plusieurs évaluations)
 */
@Entity
// @DiscriminatorValue : définit la valeur du discriminant pour identifier les étudiants
@DiscriminatorValue(value = "ETUDIANT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant extends Personne {

    // Numéro unique d'étudiant (code national de l'étudiant)
    @Column(unique = true, nullable = false, length = 20)
    private String codeEtudiant;

    // Autre identifiant : CNE (Carte Nationale d'Étudiant)
    @Column(unique = true, length = 20)
    private String cne;

    // Année d'inscription
    @Column(name = "date_inscription")
    private LocalDate dateInscription;

    // Statut de l'étudiant (ACTIF, SUSPENDU, DIPLOME, etc.)
    @Column(nullable = false, length = 20)
    private String statut = "ACTIF";

    /**
     * @ManyToOne : relation plusieurs-à-un
     * Plusieurs étudiants appartiennent à UNE filière
     *
     * @JoinColumn : spécifie la colonne de clé étrangère
     * name = "filiere_id" : nom de la colonne en base de données
     * nullable = false : un étudiant doit appartenir à une filière
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    /**
     * @OneToMany : relation un-à-plusieurs
     * Un étudiant peut avoir plusieurs évaluations
     * mappedBy = "etudiant" : indique que la relation est gérée par l'attribut 'etudiant' dans Evaluation
     *
     * cascade = CascadeType.ALL : les opérations (persist, merge, remove) se propagent
     * orphanRemoval = true : les évaluations orphelines sont supprimées automatiquement
     */
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;

    // Moyenne générale (exemple d'attribut calculé ou stocké)
    @Column(name = "moyenne_generale", columnDefinition = "DECIMAL(5,2) DEFAULT 0")
    private Double moyenneGenerale = 0.0;
}
