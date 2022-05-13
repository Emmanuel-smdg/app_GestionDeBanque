package com.emma.banking_app.dtos;

import com.emma.banking_app.entities.Compte;
import com.emma.banking_app.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
public class OperationDto {
    private Long id ;
    private Date operationDate;
    private double montant;
    private OperationType type ;
    private String description ;
}
