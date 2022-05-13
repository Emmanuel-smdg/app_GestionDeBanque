package com.emma.banking_app.dtos;

import com.emma.banking_app.entities.Client;
import com.emma.banking_app.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CompteCourantDto extends CompteDto{

    private String id ; ;
    private double solde ;
    private Date createdAt ;
    private AccountStatus status;
    private Client clientDto ;
    private double decouvert ;



}
