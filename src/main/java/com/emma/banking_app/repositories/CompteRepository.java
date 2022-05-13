package com.emma.banking_app.repositories;

import com.emma.banking_app.entities.Compte;
import com.emma.banking_app.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
