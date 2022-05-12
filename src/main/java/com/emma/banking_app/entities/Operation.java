package com.emma.banking_app.entities;

import com.emma.banking_app.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Date operationDate;
    private double montant;
    @Enumerated(EnumType.STRING)
    private OperationType type ;
    @ManyToOne
    private Compte compte ;
}
