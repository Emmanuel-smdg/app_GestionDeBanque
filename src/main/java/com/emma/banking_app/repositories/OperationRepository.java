package com.emma.banking_app.repositories;

import com.emma.banking_app.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation, Long> {
}
