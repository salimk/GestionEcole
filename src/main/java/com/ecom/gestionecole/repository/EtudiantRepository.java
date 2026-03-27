package com.ecom.gestionecole.repository;

import com.ecom.gestionecole.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
