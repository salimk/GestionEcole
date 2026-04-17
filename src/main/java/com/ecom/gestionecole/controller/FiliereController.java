package com.ecom.gestionecole.controller;

import com.ecom.gestionecole.entity.Filiere;
import com.ecom.gestionecole.repository.FiliereRepository;
import com.ecom.gestionecole.services.FiliereServiceImpl;
import com.ecom.gestionecole.services.IFiliereService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filiere")
@AllArgsConstructor
public class FiliereController {
    final FiliereServiceImpl filiereServiceImpl;


    @GetMapping
    public List<Filiere> listerFilieres(){
        return filiereServiceImpl.getAllfilieres();
    }

    @GetMapping("/{id}")
    public Filiere getFiliereByid(@PathVariable Long id) {
        return filiereServiceImpl.AfficherFiliere(id);
    }

    @PostMapping("/new")
    public Filiere createFiliere(@RequestBody Filiere filiere) {
        return filiereServiceImpl.AjouterFiliere(filiere);
    }

    @DeleteMapping("/del/{id}")
    public void deleteFiliere(@PathVariable Long id) {
        filiereServiceImpl.supprimerfiliere(id);
    }
}
