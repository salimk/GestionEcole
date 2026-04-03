package com.ecom.gestionecole.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entité représentant une évaluation (note, contrôle, examen).
 * Enregistre les résultats des étudiants dans les différentes matières.
 *
 * Annotations JPA utilisées :
 * - @Entity : marque la classe comme une entité JPA
 * - @Id : désigne le champ comme clé primaire
 * - @GeneratedValue : configure la génération automatique
 * - @ManyToOne : relation plusieurs-à-un
 * - @Version : pour le contrôle d'accès optimiste (optimistic locking)
 * - @Temporal : pour les dates (optionnel avec LocalDate/LocalDateTime)
 * - @CreationTimestamp / @UpdateTimestamp : automatise les timestamps
 */
@Entity
@Table(name = "evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Evaluation {

    // @Id : désigne le champ comme clé primaire
    // @GeneratedValue : génère automatiquement la valeur
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Type d'évaluation (CONTROLE, EXAMEN, TP, PROJET, etc.)
    @Column(nullable = false, length = 50)
    private String type;

    // Note obtenue (sur 20, sur 100, etc.)
    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double note;

    // Note maximale possible (ex: 20, 100)
    @Column(name = "note_max", nullable = false, columnDefinition = "DECIMAL(5,2) DEFAULT 20")
    private Double noteMax = 20.0;

    // Commentaires/Remarques sur l'évaluation
    @Column(length = 500, columnDefinition = "TEXT")
    private String commentaires;

    // Date de l'évaluation
    @Column(name = "date_evaluation", nullable = false)
    private LocalDate dateEvaluation;

    // Date de création du record
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    // Date de la dernière modification
    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    // Coefficient/Poids de cette évaluation dans la moyenne
    @Column(columnDefinition = "DECIMAL(3,1) DEFAULT 1.0")
    private Double coefficient = 1.0;

    // Statut (ENREGISTREE, VALIDEE, CONTESTEE, etc.)
    @Column(length = 50)
    private String statut = "ENREGISTREE";

    // @Version : pour l'optimistic locking (contrôle de concurrence)
    // Chaque modification incrémente cette version
    @Version
    @Column(name = "version")
    private Integer version = 0;

    /**
     * @ManyToOne : relation plusieurs-à-un
     * Plusieurs évaluations sont effectuées pour UN étudiant
     *
     * fetch = FetchType.LAZY : charge l'étudiant à la demande
     * nullable = false : une évaluation doit être associée à un étudiant
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;

    /**
     * @ManyToOne : relation plusieurs-à-un
     * Plusieurs évaluations concernent UNE matière
     *
     * fetch = FetchType.LAZY : charge la matière à la demande
     * nullable = false : une évaluation doit être associée à une matière
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matiere_id", nullable = false)
    private Matiere matiere;

    /**
     * @ManyToOne : relation plusieurs-à-un
     * Plusieurs évaluations sont créées par UN professeur
     *
     * fetch = FetchType.LAZY : charge le professeur à la demande
     * nullable = false : une évaluation doit être créée par un professeur
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professeur_id", nullable = false)
    private Professeur professeur;

    /**
     * Convertit la note en pourcentage
     */
    public Double getPourcentage() {
        if (noteMax == null || noteMax == 0) return 0.0;
        return (note / noteMax) * 100.0;
    }

    /**
     * Détermine la mention (Excellent, Bon, Satisfaisant, etc.)
     */
    public String getMention() {
        Double pourcentage = getPourcentage();
        if (pourcentage >= 90) return "EXCELLENT";
        else if (pourcentage >= 80) return "TRES_BON";
        else if (pourcentage >= 70) return "BON";
        else if (pourcentage >= 60) return "SATISFAISANT";
        else if (pourcentage >= 50) return "ACCEPTABLE";
        else return "INSUFFISANT";
    }

    /**
     * Indique si l'étudiant a réussi (généralement note >= 10/20)
     */
    public Boolean isReussi() {
        return note >= (noteMax / 2.0);
    }

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        dateModification = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateModification = LocalDateTime.now();
    }
}
