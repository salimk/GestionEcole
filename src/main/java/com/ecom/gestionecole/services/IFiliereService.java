package com.ecom.gestionecole.services;

import com.ecom.gestionecole.entity.Filiere;

import java.util.List;

public interface IFiliereService {
    void supprimerfiliere(Long id);
    Filiere AjouterFiliere(Filiere filiere);
    Filiere modifierfiliere(Filiere filiere);
    Filiere AfficherFiliere(Long id);
    List<Filiere> getAllfilieres();
}
