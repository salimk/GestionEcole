package com.ecom.gestionecole.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * Entité représentant une matière (cours).
 *
 * Annotations JPA utilisées :
 * - @Entity : marque la classe comme une entité JPA
 * - @Id : désigne le champ comme clé primaire
 * - @GeneratedValue : configure la génération automatique
 * - @ManyToOne : relation plusieurs-à-un
 * - @OneToMany : relation un-à-plusieurs
 * - @ManyToMany : relation plusieurs-à-plusieurs
 * - @Lob : pour les champs de grande taille (texte long)
 */
@Entity
@Table(name = "matieres", indexes = {
    @Index(name = "idx_matiere_libelle", columnList = "libelle"),
    @Index(name = "idx_matiere_code", columnList = "code")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {

    // @Id : désigne le champ comme clé primaire
    // @GeneratedValue : génère automatiquement la valeur
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Libellé/Nom de la matière
    @Column(nullable = false, length = 150)
    private String libelle;

    // Code unique de la matière (ex: MAT101, MATH2024)
    @Column(unique = true, nullable = false, length = 20)
    private String code;

    // Description détaillée de la matière
    // @Lob : indique que c'est un champ de grande taille (CLOB/BLOB en base)
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String description;

    // Nombre d'heures de cours
    @Column(name = "nombre_heures", nullable = false)
    private Integer nombreHeures;

    // Nombre de crédits (ECTS ou autre système)
    @Column(name = "nombre_credits")
    private Integer nombreCredits;

    // Coefficient de la matière dans le calcul de la moyenne
    @Column(nullable = false, columnDefinition = "DECIMAL(3,1) DEFAULT 1.0")
    private Double coefficient = 1.0;

    // Semestre (1, 2, 3, 4, etc.)
    @Column(nullable = false)
    private Integer semestre;

    // Type de matière (THEORIQUE, PRATIQUE, MIXTE)
    @Column(length = 50)
    private String type = "MIXTE";

    // Statut (ACTIVE, INACTIVE, ARCHIVEE)
    @Column(length = 50)
    private String statut = "ACTIVE";

    /**
     * @ManyToOne : relation plusieurs-à-un
     * Plusieurs matières appartiennent à UNE filière
     *
     * fetch = FetchType.LAZY : charge la filière à la demande
     * nullable = false : une matière doit appartenir à une filière
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    /**
     * @OneToMany : relation un-à-plusieurs
     * Une matière a plusieurs évaluations (contrôles, examens, etc.)
     *
     * mappedBy = "matiere" : indique que la relation est gérée par l'attribut 'matiere' dans Evaluation
     * cascade = CascadeType.ALL : propage les opérations
     * orphanRemoval = true : supprime les évaluations orphelines
     */
    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, orphanRemoval = true,
               fetch = FetchType.LAZY)
    private List<Evaluation> evaluations;

    /**
     * @ManyToMany : relation plusieurs-à-plusieurs (inverse)
     * Une matière est enseignée par plusieurs professeurs
     * Un professeur enseigne plusieurs matières
     *
     * mappedBy = "matieres" : indique que la relation est gérée par l'attribut 'matieres' dans Professeur
     */
    @ManyToMany(mappedBy = "matieres", fetch = FetchType.LAZY)
    private List<Professeur> professeurs;
}
