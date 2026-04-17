package com.ecom.gestionecole.services;

import com.ecom.gestionecole.entity.Filiere;
import com.ecom.gestionecole.repository.FiliereRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FiliereServiceImpl implements IFiliereService{

    final FiliereRepository filiereRepo;

    @Override
    public void supprimerfiliere(Long id) {
        filiereRepo.deleteById(id);
    }

    @Override
    public Filiere AjouterFiliere(Filiere filiere) {
        return filiereRepo.save(filiere);
    }

    @Override
    public Filiere modifierfiliere(Filiere newfiliere) {
        Filiere odlfiliere=filiereRepo.findById(newfiliere.getId()).orElseThrow();
        odlfiliere.setNom(newfiliere.getNom());
        odlfiliere.setNiveau(newfiliere.getNiveau());
        return filiereRepo.save(odlfiliere);
    }

    @Override
    public Filiere AfficherFiliere(Long id) {
        return filiereRepo.findById(id).orElseThrow();
    }

    @Override
    public List<Filiere> getAllfilieres() {
        return filiereRepo.findAll();
    }
}
