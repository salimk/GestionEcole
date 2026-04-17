package com.ecom.gestionecole.services;

import com.ecom.gestionecole.entity.Etudiant;
import com.ecom.gestionecole.entity.Matiere;

import java.util.List;
import java.util.Optional;

public interface IMatiereService {
    Matiere ajouterMatiere(Matiere matiere);
    Matiere getMatiereById(Long id);
    Matiere updateMatiere(Matiere matiere);
    void deleteMatiere(Long id);
    List<Matiere> getAllMatieres();
    Optional<Matiere> getMatiereByIdOp(Long id);
    List<Matiere> findEtudiantBylibelleCode(String libelle, String code);

}
