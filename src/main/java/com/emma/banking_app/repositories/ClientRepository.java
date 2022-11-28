package com.emma.banking_app.repositories;

import com.emma.banking_app.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("Select c from Client c where c.name like :mc")
    List<Client> searchClient(@Param("mc") String motcle);

}
