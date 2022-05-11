package com.emma.banking_app.entities;

import com.emma.banking_app.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class Operation {
    private Long id ;
    private Date operationDate;
    private double montant;
    private OperationType type ;
    private Compte compte ;
}
