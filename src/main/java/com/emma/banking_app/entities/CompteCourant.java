package com.emma.banking_app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CompteCourant extends Compte{
    private double decouvert ;
}
