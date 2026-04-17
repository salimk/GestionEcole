package com.ecom.gestionecole.services;

import com.ecom.gestionecole.entity.Etudiant;

import java.util.List;

public interface IEtudiantService {
    Etudiant AjouterEtudiant(Etudiant etudiant);
    Etudiant getEtudiantById(Long id);
    Etudiant updateEtudiant(Etudiant etudiant);
    void deleteEtudiant(Long id);
    List<Etudiant> getAllEtudiants();
    List<Etudiant> findEtudiantByNom(String nom);
}
