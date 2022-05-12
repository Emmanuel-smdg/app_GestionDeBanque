package com.emma.banking_app.repositories;

import com.emma.banking_app.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
