package com.ecom.gestionecole.services;

import com.ecom.gestionecole.entity.Matiere;
import com.ecom.gestionecole.repository.MatiereRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereServiceImpl implements IMatiereService{

    //Injection de la dependance repo dans service
    private final MatiereRepository matiereRep;
    public MatiereServiceImpl(MatiereRepository matiereRepository) {
        this.matiereRep = matiereRepository;
    }

    @Override
    public Matiere ajouterMatiere(Matiere matiere) {
        return matiereRep.save(matiere);
    }

    @Override
    public Matiere getMatiereById(Long id) {
        return matiereRep.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Matire not found"+id));
    }


    @Override
    @Transactional
    public Matiere updateMatiere(Matiere matiere) {
        Matiere matiereold = getMatiereById(matiere.getId());
        matiere.setLibelle(matiereold.getLibelle());
        matiere.setCoefficient(matiereold.getCoefficient());
        matiere.setCode(matiereold.getCode());
        matiere.setFiliere(matiereold.getFiliere());
        return matiereRep.save(matiereold);
    }

    @Override
    public void deleteMatiere(Long id) {
        matiereRep.deleteById(id);
    }

    @Override
    public List<Matiere> getAllMatieres() {
        return matiereRep.findAll();
    }

    @Override
    public Optional<Matiere> getMatiereByIdOp(Long id) {
        return matiereRep.findById(id);
    }

    @Override
    public List<Matiere> findEtudiantBylibelleCode(String libelle, String code) {
        return matiereRep.findByLibelleContainsIgnoreCaseAndCodeContainsIgnoreCase(libelle, code);
    }
}
