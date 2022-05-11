package com.emma.banking_app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
@Data @NoArgsConstructor @AllArgsConstructor
public class CompteEpargne extends Compte{
    private double taux ;
}
