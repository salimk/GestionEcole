package com.ecom.gestionecole.repository;

import com.ecom.gestionecole.entity.Professeur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {
}
