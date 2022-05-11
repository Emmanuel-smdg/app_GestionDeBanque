package com.emma.banking_app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CC")
@Data @AllArgsConstructor @NoArgsConstructor
public class CompteCourant extends Compte{
    private double decouvert ;
}
