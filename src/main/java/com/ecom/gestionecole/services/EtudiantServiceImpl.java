package com.ecom.gestionecole.services;

import com.ecom.gestionecole.entity.Etudiant;
import com.ecom.gestionecole.repository.EtudiantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.query.JpaEntityGraph;

import java.util.List;
import java.util.Optional;

public class EtudiantServiceImpl implements IEtudiantService{

    private EtudiantRepository etdRep;
    public EtudiantServiceImpl(EtudiantRepository etudiantRepository) {
        this.etdRep = etudiantRepository;
    }

    @Override
    public Etudiant AjouterEtudiant(Etudiant etudiant) {
        return etdRep.save(etudiant);
    }

    @Override
    public Etudiant getEtudiantById(Long id) {
        return etdRep.findById(id).orElseThrow();
    }

    @Override
    public Etudiant updateEtudiant(Etudiant etudiant) {
       Etudiant etudiantold = getEtudiantById(etudiant.getId());
       etudiantold.setNom(etudiant.getNom());
       etudiantold.setPrenom(etudiant.getPrenom());
       etudiantold.setEmail(etudiant.getEmail());
       etudiantold.setTelephone(etudiant.getTelephone());
       etudiantold.setFiliere(etudiant.getFiliere());
       return etdRep.save(etudiantold);
    }

    @Override
    public void deleteEtudiant(Long id) {
        etdRep.deleteById(id);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etdRep.findAll();
    }

    @Override
    public List<Etudiant> findEtudiantByNom(String nom) {
        return etdRep.findEtudiantByNomContaining(nom);
    }

}
