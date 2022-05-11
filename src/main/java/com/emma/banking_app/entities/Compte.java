package com.emma.banking_app.entities;

import com.emma.banking_app.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class Compte {
    private String id ;
    private double solde ;
    private Date createdAt ;
    private AccountStatus status;
    private Client client ;
    private List<Operation> operations ;



}
