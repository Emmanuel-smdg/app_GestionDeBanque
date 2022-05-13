package com.emma.banking_app.repositories;

import com.emma.banking_app.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByCompteId(String compteId);
    Page<Operation> findByCompteId(String compteId, Pageable pageable);

}
