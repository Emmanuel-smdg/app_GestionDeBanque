package com.emma.banking_app.entities;

import com.emma.banking_app.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
@Data @AllArgsConstructor @NoArgsConstructor
public abstract class Compte {
    @Id
    private String id ; ;
    private double solde ;
    private Date createdAt ;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Client client ;
    @OneToMany(mappedBy = "compte")
    private List<Operation> operations ;



}
