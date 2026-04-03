package com.ecom.gestionecole.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Classe abstraite représentant une personne dans le système de gestion d'école.
 * Utilise l'héritage JPA de type SINGLE_TABLE pour les entités Etudiant et Professeur.
 *
 * Annotations Lombok utilisées :
 * - @Data : génère automatiquement getters, setters, toString, equals, hashCode
 * - @NoArgsConstructor : génère un constructeur sans arguments
 * - @AllArgsConstructor : génère un constructeur avec tous les arguments
 * - @ToString(exclude = ...) : exclut certains champs du toString
 * - @EqualsAndHashCode : génère equals et hashCode
 */
@Entity
// @Table : configure le nom et les paramètres de la table en base de données
@Table(name = "personnes")
// @Inheritance : définit la stratégie d'héritage (SINGLE_TABLE = une seule table pour tous les types)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn : colonne qui distingue les types d'entités (ETUDIANT, PROFESSEUR)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Personne {

    // @Id : désigne le champ comme clé primaire
    // @GeneratedValue : configure la génération automatique de la valeur (IDENTITY = auto-increment)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column : configure les propriétés de la colonne
    // nullable = false : la colonne ne peut pas être NULL en base
    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    // unique = true : garantit l'unicité de la valeur dans la base
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 15, name = "telephone")
    private String telephone;

    // @Column avec updatable = false : la colonne ne peut pas être modifiée après insertion
    @Column(name = "date_creation", nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_modification")
    private LocalDateTime dateModification;

    // Adresse : exemple d'attribut supplémentaire
    @Column(length = 255)
    private String adresse;

    /**
     * Retourne le nom complet de la personne (prénom + nom)
     */
    public String getNomComplet() {
        return prenom + " " + nom;
    }
}
